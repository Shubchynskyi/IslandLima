package com.javarush.island.shubchynskyi;

import com.javarush.island.shubchynskyi.entity.EntityFactory;
import com.javarush.island.shubchynskyi.entity.gamefield.GameField;
import com.javarush.island.shubchynskyi.services.GameWorker;
import com.javarush.island.shubchynskyi.view.ConsoleView;
import com.javarush.island.shubchynskyi.view.View;

import java.time.LocalTime;


public class Runner {
    public static void main(String[] args) {
        GameField gamefield = new GameField(new EntityFactory());
        View viewer = new ConsoleView(gamefield);
        GameWorker gameWorker = new GameWorker(gamefield, viewer);
        gameWorker.run();

//        viewer.showMap();
//        viewer.showStatistic();
//
//        for (int i = 0; i < 1000; i++) {
//            System.out.println(LocalTime.now());
//            gamefield.makeStep();
//            viewer.showStatistic();
//        }


//        Cell cell = gamefield.gameMap[1][1];
//        System.out.println(cell);
//
//        gamefield.printState();




//        gamefield.makeStep();
//        gamefield.printState();

//        cell = gamefield.gameMap[1][1];
//        System.out.println(cell);


//        String type = "\uD83D\uDC17";
//
//        Cell cell = gamefield.gameField[1][1];
//        Animal animal = cell.animalsInCell.get(type).stream().findAny().get();
//        System.out.println(cell.animalsInCell.get(type).size());
//
//        System.out.println(cell);
//        System.out.println("Вес при рождении - " + animal.getWeight());
//        animal.weightLoss();
//        System.out.println("Вес после потери веса - " + animal.getWeight());
//
//        animal.eat();
//        System.out.println("Вес после еды - " + animal.getWeight());
//        System.out.println(cell);
//
//        System.out.println(cell.animalsInCell.get(type).size());
//
//        System.out.println(animal.getChancesToEat());



    }
}
