package com.javarush.island.shubchynskyi;

import com.javarush.island.shubchynskyi.entity.EntityFactory;
import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.entity.gamefield.GameField;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class Runner {
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
