package com.javarush.island.shubchynskyi.entity.plants;

import com.javarush.island.shubchynskyi.entity.animals.Organism;
import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.settings.EntitySettings.EntityEnums;
import com.javarush.island.shubchynskyi.utils.FieldCreator;
import com.javarush.island.shubchynskyi.utils.Generator;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static com.javarush.island.shubchynskyi.settings.Constants.*;
import static com.javarush.island.shubchynskyi.settings.EntitySettings.plantPrototypes;

public abstract class Plant implements Organism, Cloneable {

    private static final AtomicInteger plantCount = new AtomicInteger(0);

    private String name;
    private final EntityEnums type;
    private double weight;
    private final int maxPerCell;
    private final String avatar;

    public double getMaxWeight() {
        return maxWeight;
    }

    private final double maxWeight;
    private boolean isAlive = true;
    private Cell currentCell;

    public Plant() {
        this.name = (String) FieldCreator.getField(this, NAME);
        this.type = (EntityEnums) FieldCreator.getField(this, TYPE);
        this.weight = (double) FieldCreator.getField(this, WEIGHT);
        this.maxPerCell = (int) FieldCreator.getField(this, MAX_PER_CELL);
        this.avatar = (String) FieldCreator.getField(this, AVATAR);
        maxWeight = weight;
    }

    public String getName() {
        return name;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxPerCell() {
        return maxPerCell;
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }


    public void decreaseWeight(double weight) {
        this.weight = this.weight - weight;
    }
    public void increaseWeight(double weight) {
        this.weight = this.weight - weight;
    }


    @Override
    public void startLife() {
        grow();
    }

    // TODO задать шанс роста для каждого типа

    /**
     * Размножение растений
     * Растение с небольшим весом дает такое же потомство, но есть шанс роста
     * Есть шанс заселения соседних ячеек если в них не осталось растений
     */
    public void grow() {
        if (Generator.getRandom(0, 3) == 0) {
            getCurrentCell().getLock().lock();
            try {
                int toSpawn = getMaxPerCell() - getCurrentCell().plantsInCell.get(getAvatar()).size();
                if (toSpawn > 0) {
                    for (Plant plantPrototype : plantPrototypes) {
                        if (plantPrototype.getAvatar().equals(getAvatar())) {
                            if (toSpawn == 1) {
                                getCurrentCell().plantsInCell.get(getAvatar()).add(clone(getCurrentCell()));
                            } else {
                                toSpawn = Generator.getRandom(0, toSpawn);
                                for (int i = 0; i < toSpawn; i++) {
                                    getCurrentCell().plantsInCell.get(getAvatar()).add(clone(getCurrentCell()));
                                }
                            }
                            break;
                        }
                    }
                }
                if (Generator.getRandom(0, 5) == 0) {
                    for (Cell neighbour : getCurrentCell().getNeighbours()) {
                        if (neighbour.plantsInCell.get(getAvatar()).size() == 0) {
                            neighbour.plantsInCell.get(getAvatar()).add(clone(neighbour));
                        }
                    }
                    if(getWeight() < getMaxWeight()) {
                        increaseWeight((getMaxWeight() - getWeight())/5);
                    }
                }
            } finally {
                getCurrentCell().getLock().unlock();
            }


        }

//        } finally {
//            getCurrentCell().getLock().unlock();
//        }
    }


//    public void spawn() {

//        for (Plant plantPrototype : plantPrototypes) {
//            if (Generator.getRandom(0,5) == 0) {
//                int toSpawn = plantPrototype.getMaxPerCell() - plantsInCell.get(plantPrototype.getAvatar()).size();
//                if (toSpawn > 1) {
//                    toSpawn = Generator.getRandom(0, toSpawn);
//                    for (int i = 0; i < toSpawn; i++) {
//                        plantsInCell.get(plantPrototype.getAvatar()).add(plantPrototype.clone(this));
//                    }
//                } else if (toSpawn == 1) plantsInCell.get(plantPrototype.getAvatar()).add(plantPrototype.clone(this));
//            }
//        }

//        if (Generator.getRandom(0, 2) == 0) {
//            int maxSpawn = maxPerCell - getCurrentCell().plantsInCell.get(getAvatar()).size();
//            if (maxSpawn > 1) {
//                int toSpawn = Generator.getRandom(0, maxSpawn);
//                for (Plant plantPrototype : plantPrototypes) {
//                    if(plantPrototype.avatar.equals(getAvatar())) {
//                        for (int i = 0; i < toSpawn; i++) {
//                            getCurrentCell().plantsInCell.get(getAvatar()).add(plantPrototype.clone(getCurrentCell()));
//                        }
//                    }
//                }
//            } else if (maxSpawn == 1) {
//                for (Plant plantPrototype : plantPrototypes) {
//                    if(plantPrototype.avatar.equals(getAvatar())) {
//                        getCurrentCell().plantsInCell.get(getAvatar()).add(plantPrototype.clone(getCurrentCell()));
//                    }
//                }
//            }
//        }
//    }

    public void dead() {
        getCurrentCell().getLock().lock();
        try {
        getCurrentCell().plantsInCell.get(getAvatar()).remove(this);
        setAlive(false);
        } finally {
            getCurrentCell().getLock().unlock();
        }
    }

    public Plant clone(Cell cell) {
        try {
            Plant result = (Plant) super.clone();
            result.name = result.name + " " + plantCount.incrementAndGet();
            result.setCurrentCell(cell);
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
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
}
