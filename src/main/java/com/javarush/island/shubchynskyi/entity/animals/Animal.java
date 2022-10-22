package com.javarush.island.shubchynskyi.entity.animals;

import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import com.javarush.island.shubchynskyi.utils.FieldCreator;
import com.javarush.island.shubchynskyi.utils.Generator;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.javarush.island.shubchynskyi.settings.Constants.*;
import static com.javarush.island.shubchynskyi.settings.EntitySettings.EntityEnums;
import static com.javarush.island.shubchynskyi.settings.EntitySettings.animalPrototypes;


public abstract class Animal implements Cloneable {

    private static final AtomicInteger animalCount = new AtomicInteger(0);

    //TODO вынести в конкретный класс для создания списка одного типа
//    private Set<Animal> oneTypeAnimals = new HashSet<>();


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
    private final double criticalWeight; // TODO добавить уменьшение веса после хода
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

    public Map<String, Integer> getChancesToEat() {
        return chancesToEat;
    }

    public void setChancesToEat(Map<String, Integer> chancesToEat) {
        this.chancesToEat = chancesToEat;
    }

//    public Map<String, Integer> getChancesToEat() {
//        return chancesToEat;
//    }


    public void increaseWeight(double weight) {
        this.weight = this.weight + weight;
    }

    public void decreaseWeight(double weight) {
        this.weight = this.weight - weight;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public EntityEnums getType() {
        return type;
    }

    public String getName() {
        return name;
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


    // TODO убрать лишнее
    public void move() {

        int stepCount = Generator.getRandom(0, getSpeed() + 1);
        if (stepCount == 0) return;

        //делаю шаги
        for (int i = 0; i < stepCount; i++) {
            //беру рандомную соседскую доступную ячейку
            int count = Generator.getRandom(0, getCurrentCell().getNeighbours().size());
//            System.out.println("Хочу шагать в ячейку " + getCurrentCell().getNeighbours().get(count).toString());

            // проверка на максимум животных
            int maxAnimalInCell = getCurrentCell().getNeighbours().get(count).animalsInCell.get(getAvatar()).size();
//            System.out.printf("В целевой ячейке %d животных. Доступный максимум - %d%n", maxAnimalInCell, getMaxPerCell());

            if (getMaxPerCell() > maxAnimalInCell) {
                // новая ячейка стала текущей, если позволяет место
                getCurrentCell().animalsInCell.get(getAvatar()).remove(this);
                setCurrentCell(getCurrentCell().getNeighbours().get(count));
                getCurrentCell().animalsInCell.get(getAvatar()).add(this);

            }
            //если места нет, ход пропущен
        }
        weightLoss();
    }

    // TODO задать шансы и количество детенышей в зависимости от типа
    //нужно вводить очередь, т.к. объекты ходят и размножатся повторно
    public void spawn() {
        // генерируем число - шанс 10% что пойдем дальше
        int spawnChance = Generator.getRandom(0, 20);
        if (spawnChance == 0) {
            int maxBaby = maxPerCell - getCurrentCell().animalsInCell.get(getAvatar()).size();
            if (maxBaby > 2) maxBaby = 2;   // TODO максимум детенышей, вынести в настройки
            if (maxBaby != 0) {
                maxBaby = Generator.getRandom(1, maxBaby + 1);
                for (Animal animalPrototype : animalPrototypes) {
                    if (animalPrototype.avatar.equals(getAvatar())) {
                        for (int i = 0; i < maxBaby; i++) {
                            getCurrentCell().animalsInCell.get(getAvatar()).add(animalPrototype.clone(getCurrentCell()));
                        }
                    }
                }
            }
        }
    }


    // TODO % веса в конце хода, вынести процент потери веса в настройки
    // гусеница умирает сразу же, надо исправить
    public void weightLoss() {
        double weight = getMaxFood() / 5;      //5%
        decreaseWeight(weight);

        if (getWeight() <= criticalWeight) {
            dead();
        }
    }

    public void eat() {
        if (getWeight() < maxWeight) {
            if (this instanceof Omnivore) {
                // рандомно выбираю кого буду есть (растение или животное)
                if (Generator.getRandom(0, 2) == 1) {
                    tryToEatAnimal();
                } else tryToEatPlant();

            } else if (this instanceof Predator) {
                // выбираем животное, вызываем eat(Animal)
//                System.out.println(getName() + " пробует есть");
                tryToEatAnimal();
//

            } else {
                // едим растение, вызываем eat(Plant)
                tryToEatPlant();
            }
        }
    }

    private void tryToEatPlant() {

        Set<Map.Entry<String, Set<Plant>>> entries =
                getCurrentCell().plantsInCell.entrySet().stream()
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
        // получаю Set тех кого не ноль и кого можно есть (Set обеспечивает рандомный выбор типа жертвы)
//        System.out.println("Выбираю жертву");
        Set<Map.Entry<String, Set<Animal>>> entries =
                getCurrentCell().animalsInCell.entrySet().stream()
                        .filter(o -> o.getValue().size() > 0)
                        .filter(o -> chancesToEat.containsKey(o.getKey()))
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
//        System.out.println("жертва - " + animal.getName()); // TODO remove
        int percent = chancesToEat.get(animal.getAvatar());
        if (Generator.checkChance(percent)) {
            animal.dead();
            double maxTakeFood = maxWeight - getWeight();
            if (maxTakeFood > animal.getWeight()) {
                maxTakeFood = animal.getWeight();
            }
            increaseWeight(maxTakeFood);
        }
    }

    private void eat(Plant plant) {
        double maxTakeFood = maxWeight - getWeight();   // максимум еды котую может съесть животное
        if (maxTakeFood > plant.getWeight()) {          // если в растении меньше, то уменьшаем показатель
            maxTakeFood = plant.getWeight();
        }
        increaseWeight(maxTakeFood);                    // увеличиваем текущий вес
        plant.decreaseWeight(maxTakeFood);              // уменьшаем вес растения

        if (plant.getWeight() <= 0) {                   // если вес растения 0, то оно мертво
            plant.dead();
        }
    }


    public void dead() {
//        System.out.println(getName() + " должен быть мертв");
        boolean b = getCurrentCell().animalsInCell.get(getAvatar()).remove(this);
//        System.out.println(getName() + " удален из списка? - " + b);
        setAlive(false);
//        System.out.println(getName() + " жив? - " + isAlive());
//        System.out.println("еще в списке? - " + getCurrentCell().animalsInCell.get(getAvatar()).contains(this));
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

    @Override
    public Animal clone() {
        try {
            Animal result = (Animal) super.clone();
            result.name = result.name + " " + animalCount.incrementAndGet();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
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

    public String getAvatar() {
        return avatar;
    }
}
