package com.javarush.island.shubchynskyi;

import com.javarush.island.shubchynskyi.entity.EntityFactory;
import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.entity.gamefield.GameField;


public class ConsoleRunner {
    public static void main(String[] args) {
        GameField gamefield = new GameField(new EntityFactory());

        Cell cell;
//        Animal animal = cell.animalsInCell.get("\uD83D\uDC0E").stream().findAny().get();
//        System.out.println(cell.animalsInCell.get("\uD83D\uDC0E").size());
//        animal.move();
//        System.out.println(cell.animalsInCell.get("\uD83D\uDC0E").size());

//        cell = gamefield.gameField[1][1];
//        System.out.println(cell);
        gamefield.printState();
        gamefield.makeStep();
        gamefield.printState();
//        cell = gamefield.gameField[1][1];
//        System.out.println(cell);


    }
}
