package com.javarush.island.shubchynskyi.entity.gamefield;

import com.javarush.island.shubchynskyi.entity.animals.Animal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cell {


    // TODO соседние валидные ячейки
    List<Cell> neighbours = new ArrayList<>();

    public void addNeighbour (Cell cell) {
        neighbours.add(cell);
    }

//    @Override
//    public String toString() {
//        List<String> collect = animalsInCell.stream().map(Animal::getName).toList();
//        return "Cell " + ", animalsInCell=" + collect;
//    }

    // TODO заполнить рандомно при создании ячейки
    public Set<Animal> animalsInCell = new HashSet<>();


    //все животные должны поесть, подвигаться, по размножаться
    // как вариант координаты будут храниться в обьектах, в каждом обьекте или списке обьектов одного класса
    //
    public void makeStep() {

    }


}
