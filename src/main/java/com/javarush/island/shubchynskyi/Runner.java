package com.javarush.island.shubchynskyi;

import com.javarush.island.shubchynskyi.entity.EntityFactory;
import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.entity.gamefield.GameField;


public class Runner {
    public static void main(String[] args) {
        GameField gamefield = new GameField(new EntityFactory());
        gamefield.printState();

//        Cell cell = gamefield.field[1][1];
//
//
//
//        for (var var :  cell.plantsInCell.entrySet()) {
//            System.out.println(var.getValue());
//        }




//        for (Animal prototype : entityFactory.prototypes) {
//            System.out.println(prototype.getName());
//        }
//
//        Animal horse = entityFactory.getAnimal(HORSE);
//
//        System.out.println();
//        System.out.println(horse.getName());
//        System.out.println(horse.getMaxFood());
//        System.out.println(horse.getMaxPerCell());
//        System.out.println(horse.getSpeed());
//        System.out.println(horse.getWeight());


    }
}
