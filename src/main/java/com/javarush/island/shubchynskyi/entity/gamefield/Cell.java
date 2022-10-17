package com.javarush.island.shubchynskyi.entity.gamefield;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import com.javarush.island.shubchynskyi.preferences.AnimalPref;
import com.javarush.island.shubchynskyi.preferences.PlantPref;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Cell {
    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private final List<Cell> neighbours = new ArrayList<>();
    public Map<AnimalPref.AnimalEnums, Set<Animal>> animalsInCell = new ConcurrentHashMap<>();

    public Map<PlantPref.PlantEnums, Set<Plant>> plantsInCell = new ConcurrentHashMap<>();


    public List<Cell> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour (Cell cell) {
        neighbours.add(cell);
    }

    //TODO remove when finish

    @Override
    public String toString() {
        return "Cell{" +
                ", animalsInCell=" + animalsInCell.entrySet() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y && Objects.equals(animalsInCell, cell.animalsInCell) && Objects.equals(plantsInCell, cell.plantsInCell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, animalsInCell.size(), plantsInCell.size());
    }
}
