package com.javarush.island.shubchynskyi.entity.plants;

import com.javarush.island.shubchynskyi.settings.EntitySettings.EntityEnums;
import com.javarush.island.shubchynskyi.utils.FieldCreator;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static com.javarush.island.shubchynskyi.settings.Constants.*;

public abstract class Plant implements Cloneable {

    private static final AtomicInteger plantCount = new AtomicInteger(0);

    //TODO вынести в конкретный класс для создания списка одного типа
//    private Set<Plant> oneTypeAnimals = new HashSet<>();

    private String name;
    private final EntityEnums type;
    private double weight;
    private final int maxPerCell;
    private final String avatar;
    private boolean isAlive = true;

    public Plant() {
        this.name = (String) FieldCreator.getField(this, NAME);
        this.type = (EntityEnums) FieldCreator.getField(this, TYPE);
        this.weight = (double) FieldCreator.getField(this, WEIGHT);
        this.maxPerCell = (int) FieldCreator.getField(this, MAX_PER_CELL);
        this.avatar = (String) FieldCreator.getField(this, AVATAR);
    }


    public String getAvatar() {
        return avatar;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
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

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return Double.compare(plant.weight, weight) == 0 && maxPerCell == plant.maxPerCell && isAlive == plant.isAlive && Objects.equals(name, plant.name) && type == plant.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public Plant clone() {
        try {
            Plant result = (Plant) super.clone();
            result.name = result.name + " " + plantCount.incrementAndGet();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


}
