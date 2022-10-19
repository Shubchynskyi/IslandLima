package com.javarush.island.shubchynskyi.entity.animals;

import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.utils.FieldCreator;
import com.javarush.island.shubchynskyi.utils.Generator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

import static com.javarush.island.shubchynskyi.settings.EntitySettings.EntityEnums;
import static com.javarush.island.shubchynskyi.settings.Constants.*;


public abstract class Animal implements Cloneable {

    private static final AtomicInteger animalCount = new AtomicInteger(0);

    //TODO вынести в конкретный класс для создания списка одного типа
//    private Set<Animal> oneTypeAnimals = new HashSet<>();

    private Cell currentCell;

    private String name;
    private final EntityEnums type;
    private final double weight;
    private final int maxPerCell;
    private final int speed;
    private double maxFood;
    private final String avatar;
    private boolean isAlive = true;
    public Animal() {
        this.name = (String) FieldCreator.getField(this, NAME);
        this.type = (EntityEnums) FieldCreator.getField(this, TYPE);
        this.weight = (double) FieldCreator.getField(this, WEIGHT);
        this.maxPerCell = (int) FieldCreator.getField(this, MAX_PER_CELL);
        this.speed = (int) FieldCreator.getField(this, SPEED);
        this.maxFood = (double) FieldCreator.getField(this, MAX_FOOD);
        this.avatar = (String) FieldCreator.getField(this, AVATAR);
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

    public void inMaxFood(float maxFood) {
        //TODO logic
        this.maxFood = maxFood;
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
            // удалиться из списка текущей ячейки
            getCurrentCell().animalsInCell.get(getAvatar()).remove(this);

            //беру рандомную соседскую доступную ячейку
            int count = Generator.getRandom(0, getCurrentCell().getNeighbours().size());
//            System.out.println("Хочу шагать в ячейку " + getCurrentCell().getNeighbours().get(count).toString());

            // проверка на максимум животных
            int maxAnimalInCell = getCurrentCell().getNeighbours().get(count).animalsInCell.get(getAvatar()).size();
//            System.out.printf("В целевой ячейке %d животных. Доступный максимум - %d%n", maxAnimalInCell, getMaxPerCell());

            if (getMaxPerCell() > maxAnimalInCell) {
                // новая ячейка стала текущей, если позволяет место
                setCurrentCell(getCurrentCell().getNeighbours().get(count));
                getCurrentCell().animalsInCell.get(getAvatar()).add(this);
            }
            //если места нет, ход пропущен
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
