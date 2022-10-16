package com.javarush.island.shubchynskyi.entity.gamefield;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import com.javarush.island.shubchynskyi.preferences.AnimalPref;
import com.javarush.island.shubchynskyi.preferences.PlantPref;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Cell {

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
}
