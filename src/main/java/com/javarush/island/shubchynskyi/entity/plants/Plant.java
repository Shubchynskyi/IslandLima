package com.javarush.island.shubchynskyi.entity.plants;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.exception.IslandException;
import com.javarush.island.shubchynskyi.preferences.AnimalPref;
import com.javarush.island.shubchynskyi.preferences.PlantPref;

import static com.javarush.island.shubchynskyi.preferences.PlantPref.*;
import static com.javarush.island.shubchynskyi.preferences.Preferences.*;
import static com.javarush.island.shubchynskyi.preferences.Preferences.MAX_FOOD;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Plant implements Cloneable {

    private static final AtomicInteger plantCount = new AtomicInteger(0);

    //TODO вынести в конкретный класс для создания списка одного типа
//    private Set<Plant> oneTypeAnimals = new HashSet<>();

    private String name;
    private final PlantEnums type;
    private double weight;
    private final int maxPerCell;
    private boolean isAlive = true;

    public Plant() {
        try {
            this.name = (String) PlantPref.class.getField(this.getClass().getSimpleName().toLowerCase() + NAME).get(PlantPref.class);
            this.type = (PlantPref.PlantEnums) PlantPref.class.getField(this.getClass().getSimpleName().toLowerCase() + TYPE).get(PlantPref.class);
            this.weight = PlantPref.class.getField(this.getClass().getSimpleName().toLowerCase() + WEIGHT).getDouble(PlantPref.class);
            this.maxPerCell = PlantPref.class.getField(this.getClass().getSimpleName().toLowerCase() + MAX_PER_CELL).getInt(PlantPref.class);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IslandException(e);
        }

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

    public PlantEnums getType() {
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
