package com.javarush.island.shubchynskyi.entity.animals;

import com.javarush.island.shubchynskyi.exception.IslandException;
import com.javarush.island.shubchynskyi.preferences.Preferences;

import java.util.Objects;

import static com.javarush.island.shubchynskyi.preferences.Preferences.*;

public abstract class Animal implements Cloneable {

    private final String name;
    private final double weight;
    private final int maxPerCell;
    private final int speed;
    private double maxFood;

    public boolean isAlive = true;

    public Animal() {
        try {
            this.name = (String) Preferences.class.getField(this.getClass().getSimpleName().toLowerCase() + NAME).get(Preferences.class);
            this.weight = Preferences.class.getField(this.getClass().getSimpleName().toLowerCase() + WEIGHT).getDouble(Preferences.class);
            this.maxPerCell = Preferences.class.getField(this.getClass().getSimpleName().toLowerCase() + MAX_PER_CELL).getInt(Preferences.class);
            this.speed = Preferences.class.getField(this.getClass().getSimpleName().toLowerCase() + SPEED).getInt(Preferences.class);
            this.maxFood = Preferences.class.getField(this.getClass().getSimpleName().toLowerCase() + MAX_FOOD).getDouble(Preferences.class);
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

    @Override
    public Animal clone() {
        try {
            return (Animal) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
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
        return Objects.hash(name, weight, maxPerCell, speed, maxFood, isAlive);
    }
}
