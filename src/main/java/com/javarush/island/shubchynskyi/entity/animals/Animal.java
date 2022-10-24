package com.javarush.island.shubchynskyi.entity.animals;

import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import com.javarush.island.shubchynskyi.utils.FieldCreator;
import com.javarush.island.shubchynskyi.utils.Generator;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.javarush.island.shubchynskyi.settings.Constants.*;
import static com.javarush.island.shubchynskyi.settings.EntitySettings.EntityEnums;
import static com.javarush.island.shubchynskyi.settings.Prototypes.getAnimalPrototypes;

public abstract class Animal implements Organism, Cloneable {

    private static final AtomicInteger animalCount = new AtomicInteger(0);

    private String name;
    private final EntityEnums type;
    private double weight;
    private final int maxPerCell;
    private final int speed;
    private final double maxFood;
    private final String avatar;

    private Cell currentCell;
    private boolean isAlive = true;
    private final double maxWeight;
    private final double criticalWeight;
    private Map<String, Integer> chancesToEat;

    public Animal() {
        this.name = (String) FieldCreator.getField(this, NAME);
        this.type = (EntityEnums) FieldCreator.getField(this, TYPE);
        this.weight = (double) FieldCreator.getField(this, WEIGHT);
        this.maxPerCell = (int) FieldCreator.getField(this, MAX_PER_CELL);
        this.speed = (int) FieldCreator.getField(this, SPEED);
        this.maxFood = (double) FieldCreator.getField(this, MAX_FOOD);
        this.avatar = (String) FieldCreator.getField(this, AVATAR);
        this.maxWeight = this.weight;
        this.criticalWeight = this.weight - this.maxFood;
    }

    @Override
    public void startLife() {
        move();
        eat();
        spawn();
    }

    public void increaseWeight(double weight) {
        this.weight = this.weight + weight;
    }

    public void decreaseWeight(double weight) {
        this.weight = this.weight - weight;
    }

    public void weightLoss() {
        double weight = getMaxFood() / 10;      //10%  //TODO вынести в настройки
        decreaseWeight(weight);
        if (getWeight() <= getCriticalWeight()) {
            dead();
        }
    }

    public void dead() {
        getCurrentCell().getAnimalsInCell().get(getAvatar()).remove(this);
        setAlive(false);
    }

    public void move() {
        int stepCount = Generator.getRandom(0, getSpeed() + 1);
        if (stepCount == 0) return;

        for (int i = 0; i < stepCount; i++) {
            int randomNeighbourCell = Generator.getRandom(0, getCurrentCell().getNeighbours().size());
            int animalInTargetCell = getCurrentCell().getNeighbours().get(randomNeighbourCell).getAnimalsInCell().get(getAvatar()).size();

            if (getMaxPerCell() > animalInTargetCell) {
                getCurrentCell().getAnimalsInCell().get(getAvatar()).remove(this);
                setCurrentCell(getCurrentCell().getNeighbours().get(randomNeighbourCell));
                getCurrentCell().getAnimalsInCell().get(getAvatar()).add(this);
            }
        }
        weightLoss();
    }

    // TODO задать шансы и количество детенышей в зависимости от типа
    public void spawn() {
        getCurrentCell().getLock().lock();
        try {
            if (getWeight() > getCriticalWeight() + getMaxFood() * 0.5) { //TODO вынести в настройки
                int spawnChance = Generator.getRandom(0, 20); // TODO шанс рождения, определить для каждого класса?
                if (spawnChance == 0) {
                    int maxBaby = getMaxPerCell() - getCurrentCell().getAnimalsInCell().get(getAvatar()).size();
                    if (maxBaby > 2) maxBaby = 2;   // TODO максимум детенышей, вынести в настройки
                    if (maxBaby != 0) {
                        maxBaby = Generator.getRandom(1, maxBaby + 1);
                        for (Animal animalPrototype : getAnimalPrototypes()) {
                            if (animalPrototype.getAvatar().equals(getAvatar())) {
                                for (int i = 0; i < maxBaby; i++) {
                                    getCurrentCell().getAnimalsInCell().get(getAvatar()).add(animalPrototype.clone(getCurrentCell()));
                                }
                            }
                        }
                    }
                }
            }
        } finally {
            getCurrentCell().getLock().unlock();
        }
    }


    public void eat() {
        if (getWeight() < getMaxWeight()) {
            if (this instanceof Omnivore) {
                if (Generator.getRandom(0, 2) == 1) {
                    tryToEatAnimal();
                } else tryToEatPlant();
            } else if (this instanceof Predator) {
                tryToEatAnimal();
            } else {
                tryToEatPlant();
            }
        }
    }

    private void tryToEatPlant() {
        Set<Map.Entry<String, Set<Plant>>> entries =
                getCurrentCell().getPlantsInCell().entrySet().stream()
                        .filter(o -> o.getValue().size() > 0)
                        .collect(Collectors.toSet());

        if (entries.size() > 0) {
            Objects.requireNonNull(entries.stream()
                            .findAny()
                            .orElse(null))
                    .getValue().stream()
                    .findAny()
                    .ifPresent(this::eat);
        }
    }

    private void tryToEatAnimal() {
        Set<Map.Entry<String, Set<Animal>>> entries =
                getCurrentCell().getAnimalsInCell().entrySet().stream()
                        .filter(o -> o.getValue().size() > 0)
                        .filter(o -> getChancesToEat().containsKey(o.getKey()))
                        .collect(Collectors.toSet());

        if (entries.size() > 0) {
            Objects.requireNonNull(entries.stream()
                            .findAny()
                            .orElse(null))
                    .getValue().stream()
                    .findAny()
                    .ifPresent(this::eat);
        }
    }

    private void eat(Animal animal) {
        int percent = getChancesToEat().get(animal.getAvatar());
        if (Generator.checkChance(percent)) {
            animal.dead();
            double maxTakeFood = getMaxWeight() - getWeight();
            if (maxTakeFood > animal.getWeight()) {
                maxTakeFood = animal.getWeight();
            }
            increaseWeight(maxTakeFood);
        }
    }

    private void eat(Plant plant) {
        double maxTakeFood = getMaxWeight() - getWeight();
        if (maxTakeFood > plant.getWeight()) {
            maxTakeFood = plant.getWeight();
        }
        increaseWeight(maxTakeFood);
        plant.decreaseWeight(maxTakeFood);

        if (plant.getWeight() <= 0) {
            plant.dead();
        }
    }

    public Animal clone(Cell cell) {
        try {
            Animal result = (Animal) super.clone();
            result.name = result.name + " " + animalCount.incrementAndGet();
            result.setCurrentCell(cell);
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    public String getName() {
        return name;
    }

    public EntityEnums getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxPerCell() {
        return maxPerCell;
    }

    public int getSpeed() {
        return speed;
    }

    public double getMaxFood() {
        return maxFood;
    }

    public String getAvatar() {
        return avatar;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public double getCriticalWeight() {
        return criticalWeight;
    }

    public Map<String, Integer> getChancesToEat() {
        return chancesToEat;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setChancesToEat(Map<String, Integer> chancesToEat) {
        this.chancesToEat = chancesToEat;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Double.compare(animal.weight, weight) == 0 && maxPerCell == animal.maxPerCell && speed == animal.speed && Double.compare(animal.maxFood, maxFood) == 0 && isAlive == animal.isAlive && Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
