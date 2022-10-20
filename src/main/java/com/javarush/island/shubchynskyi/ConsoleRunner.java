package com.javarush.island.shubchynskyi;

import com.javarush.island.shubchynskyi.entity.EntityFactory;
import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.entity.gamefield.GameField;


public class ConsoleRunner {
    public static void main(String[] args) {
        GameField gamefield = new GameField(new EntityFactory());

//        gamefield.printState();
//        gamefield.makeStep();
//        gamefield.printState();

        String type = "\uD83D\uDC0E";

        Cell cell = gamefield.gameField[1][1];
        Animal animal = cell.animalsInCell.get(type).stream().findAny().get();
        System.out.println(cell.animalsInCell.get(type).size());

        System.out.println(cell);
        System.out.println("Вес при рождении - " + animal.getWeight());
        animal.weightLoss();
        System.out.println("Вес после потери веса - " + animal.getWeight());

        animal.eat();
        System.out.println("Вес после еды - " + animal.getWeight());
        System.out.println(cell);

        System.out.println(cell.animalsInCell.get(type).size());

        System.out.println(animal.getChancesToEat());

//        cell = gamefield.gameField[1][1];
//        System.out.println(cell);

//        cell = gamefield.gameField[1][1];
//        System.out.println(cell);

    }
}
