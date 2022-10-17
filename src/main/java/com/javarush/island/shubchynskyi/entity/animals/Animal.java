package com.javarush.island.shubchynskyi.entity.animals;

import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.exception.IslandException;
import com.javarush.island.shubchynskyi.preferences.AnimalPref;
import com.javarush.island.shubchynskyi.utils.Generator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static com.javarush.island.shubchynskyi.preferences.AnimalPref.AnimalEnums;
import static com.javarush.island.shubchynskyi.preferences.Preferences.*;


public abstract class Animal implements Cloneable {

    private static final AtomicInteger animalCount = new AtomicInteger(0);

    //TODO вынести в конкретный класс для создания списка одного типа
//    private Set<Animal> oneTypeAnimals = new HashSet<>();

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    private Cell currentCell;
    private String name;
    private final AnimalEnums type;
    private final double weight;
    private final int maxPerCell;
    private final int speed;
    private double maxFood;
    private boolean isAlive = true;

    public Animal() {
        try {
            this.name = (String) AnimalPref.class.getField(this.getClass().getSimpleName().toLowerCase() + NAME).get(AnimalPref.class);
            this.type = (AnimalEnums) AnimalPref.class.getField(this.getClass().getSimpleName().toLowerCase() + TYPE).get(AnimalPref.class);
            this.weight = AnimalPref.class.getField(this.getClass().getSimpleName().toLowerCase() + WEIGHT).getDouble(AnimalPref.class);
            this.maxPerCell = AnimalPref.class.getField(this.getClass().getSimpleName().toLowerCase() + MAX_PER_CELL).getInt(AnimalPref.class);
            this.speed = AnimalPref.class.getField(this.getClass().getSimpleName().toLowerCase() + SPEED).getInt(AnimalPref.class);
            this.maxFood = AnimalPref.class.getField(this.getClass().getSimpleName().toLowerCase() + MAX_FOOD).getDouble(AnimalPref.class);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IslandException(e);
        }

    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void inMaxFood(float maxFood) {
        //TODO logic
        this.maxFood = maxFood;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public AnimalEnums getType() {
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

        // удалиться из списка текущей ячейки
        getCurrentCell().animalsInCell.get(getType()).remove(this);

        //делаю шаги
        for (int i = 0; i < stepCount; i++) {

            //беру рандомную соседскую доступную ячейку
            int count = Generator.getRandom(0, getCurrentCell().getNeighbours().size());

            // проверка на максимум животных
            int maxAnimalInCell = getCurrentCell().getNeighbours().get(count).animalsInCell.get(getType()).size();

            if (getMaxPerCell() < maxAnimalInCell) {
                // новая ячейка стала текущей, если позволяет место
                setCurrentCell(getCurrentCell().getNeighbours().get(count));
            }

            //если места нет, ход пропущен
        }
        // добавляюсь в список конечной ячейки
        if (getCurrentCell().animalsInCell.containsKey(getType())) {
            getCurrentCell().animalsInCell.get(getType()).add(this);
        } else {
            Set<Animal> newSet = new HashSet<>();
            newSet.add(this);
            getCurrentCell().animalsInCell.put(getType(), newSet);
        }
    }

    public void reproduction() {
        // генерируем число
        // если false - выход
        // иначе от 1 до 6 детенышей - или ввести максимум для каждого класса
        // полученное значение проверить с максимумом в ячейке и создать не более максимума (метод getAnimal в entityFactory)
        //
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
}
