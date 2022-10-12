package com.javarush.island.shubchynskyi.entity.animals;

import com.javarush.island.shubchynskyi.exception.IslandException;
import com.javarush.island.shubchynskyi.preferences.Preferences;

import static com.javarush.island.shubchynskyi.preferences.Preferences.*;

public abstract class Animal {

    private final float weight;
    private final float maxPerCell;
    private final float speed;
    private float maxFood;

    public boolean isAlive = true;

    public Animal() {
        try {
            this.weight = Preferences.class.getField(this.getClass().getSimpleName().toLowerCase() + WEIGHT).getFloat(Preferences.class);
            this.maxPerCell = Preferences.class.getField(this.getClass().getSimpleName().toLowerCase() + MAX_PER_CELL).getFloat(Preferences.class);
            this.speed = Preferences.class.getField(this.getClass().getSimpleName().toLowerCase() + SPEED).getFloat(Preferences.class);
            this.maxFood = Preferences.class.getField(this.getClass().getSimpleName().toLowerCase() + MAX_FOOD).getFloat(Preferences.class);
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

    public float getWeight() {
        return weight;
    }

    public float getMaxPerCell() {
        return maxPerCell;
    }

    public float getSpeed() {
        return speed;
    }

    public float getMaxFood() {
        return maxFood;
    }
}
