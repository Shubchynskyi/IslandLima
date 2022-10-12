package com.javarush.island.shubchynskyi.entity.gamefield;

import com.javarush.island.shubchynskyi.entity.animals.Animal;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cell {
    int x;
    int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", animalsInCell=" + animalsInCell +
                '}';
    }

    public Map<Class<? extends Animal>, Set<Animal>> animalsInCell = new HashMap<>();

    //все животные должны поесть, подвигаться, по размножаться
    // как вариант координаты будут храниться в обьектах, в каждом обьекте или списке обьектов одного класса
    //
    public void makeStep() {

    }


}
