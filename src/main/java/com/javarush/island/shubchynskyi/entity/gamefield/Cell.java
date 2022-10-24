package com.javarush.island.shubchynskyi.entity.gamefield;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import com.javarush.island.shubchynskyi.utils.Generator;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.javarush.island.shubchynskyi.settings.EntitySettings.plantPrototypes;

public class Cell {
    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private final Lock lock = new ReentrantLock(true);

    private final List<Cell> neighbours = new ArrayList<>();
    public Map<String, Set<Animal>> animalsInCell = new ConcurrentHashMap<>();
    public Map<String, Set<Plant>> plantsInCell = new ConcurrentHashMap<>();

    public List<Cell> getNeighbours() {
        return neighbours;
    }
    public Lock getLock() { return lock; }

    public void addNeighbour(Cell cell) {
        neighbours.add(cell);
    }

    // TODO remove if no need
    public void spawnPlants() {
        for (Plant plantPrototype : plantPrototypes) {
            if (Generator.getRandom(0,5) == 0) {
                int toSpawn = plantPrototype.getMaxPerCell() - plantsInCell.get(plantPrototype.getAvatar()).size();
                if (toSpawn > 1) {
                    toSpawn = Generator.getRandom(0, toSpawn);
                    for (int i = 0; i < toSpawn; i++) {
                        plantsInCell.get(plantPrototype.getAvatar()).add(plantPrototype.clone(this));
                    }
                } else if (toSpawn == 1) plantsInCell.get(plantPrototype.getAvatar()).add(plantPrototype.clone(this));
            }
        }
    }

    //TODO remove when finish
    @Override
    public String toString() {
        List<String> plantsStatistic = new ArrayList<>();
        for (var var : plantsInCell.entrySet()) {
            plantsStatistic.add(var.getKey() + " : " + var.getValue().size());
        }
        List<String> animalsStatistic = new ArrayList<>();
        for (var var : animalsInCell.entrySet()) {
            animalsStatistic.add(var.getKey() + " : " + var.getValue().size());
        }

        return "Cell [" + x + "/" + y + "] //info: \n" +
                "\t" + "cell neighbours is " + getNeighbours().size() + "\n" +
                "\t" + "plants in cell: " +
                plantsStatistic + "\n" +
                "\t" + "animals in cell: " +
                animalsStatistic;
    }
            // TODO remove

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Cell cell = (Cell) o;
//        return x == cell.x && y == cell.y && Objects.equals(animalsInCell, cell.animalsInCell) && Objects.equals(plantsInCell, cell.plantsInCell);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(x, y, animalsInCell.size(), plantsInCell.size());
//    }


}
