package com.javarush.island.shubchynskyi.entity.gamefield;

import com.javarush.island.shubchynskyi.entity.EntityFactory;
import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import com.javarush.island.shubchynskyi.settings.EntitySettings;
import com.javarush.island.shubchynskyi.settings.GameSettings;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;


public class GameField {

    private final int width = GameSettings.GAME_FIELD_WIDTH;
    private final int height = GameSettings.GAME_FIELD_HEIGHT;
    private final EntityFactory entityFactory;
    private final Cell[][] gameField = new Cell[width][height];
//    private final Map<String, Integer> statisticMap = new HashMap<>();

    public GameField(EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
        initialize();
    }

    public Cell[][] getGameField() {
        return gameField;
    }
    public EntityFactory getEntityFactory() {
        return entityFactory;
    }
    public int getWidth() { return width; }
    public int getHeight() {return height; }

    private void initialize() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                gameField[x][y] = entityFactory.getFilledCell(x, y);
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if ((y - 1) >= 0)               gameField[x][y].addNeighbour(gameField[x][y - 1]);
                if ((x + 1) <= width - 1)       gameField[x][y].addNeighbour(gameField[x + 1][y]);
                if ((y + 1) <= height - 1)      gameField[x][y].addNeighbour(gameField[x][y + 1]);
                if ((x - 1) >= 0)               gameField[x][y].addNeighbour(gameField[x - 1][y]);
            }
        }
    }

//    private void statisticMapInit() {
//        for (Animal prototype : EntitySettings.animalPrototypes) {
//            statisticMap.put(prototype.getAvatar(), 0);
//        }
//        for (Plant prototype : EntitySettings.plantPrototypes) {
//            statisticMap.put(prototype.getAvatar(), 0);
//        }
//    }
    // вывод статистики по всей карте - нужен в конце хода
    // добавить в мапу ВСЕХ по аватару, потом вынести в стрингбилдер

//    public String getStatistic() {
//        StringBuilder result = new StringBuilder();
//
//        statisticMapInit();
//
//        int oldValue;
//        int newValue;
//        String type;
//
//        for (Cell[] cells : getGameField()) {
//            for (Cell cell : cells) {
//                for (var var : cell.animalsInCell.entrySet()) {
//                    type = var.getKey(); // получил ключ
//                    newValue = var.getValue().size(); // размер сета по ключу map
//                    oldValue = statisticMap.get(type);
//                    statisticMap.put(type, oldValue + newValue);
//                }
//                for (var var : cell.plantsInCell.entrySet()) {
//                    type = var.getKey(); // получил ключ
//                    newValue = var.getValue().size(); // размер сета по ключу map
//                    oldValue = statisticMap.get(type);
//                    statisticMap.put(type, oldValue + newValue);
//                }
//            }
//        }
//
//        for (var var : statisticMap.entrySet()) {
//            result.append(var.getKey()).append(" = ").append(var.getValue()).append("; ");
//        }
//
//        return result.toString();
//    }
//





    // TODO remove
//    public void makeStep() {
//
//        for (Cell[] cells : getGameField()) {
//            for (Cell cell : cells) {
//
//                for (var var : cell.animalsInCell.entrySet()) {
//                    for (Animal animal : var.getValue()) {
//
//                        if(animal.isAlive()) {
////                            System.out.println(LocalTime.now());
//                            animal.eat();
//                            animal.move();
//                            animal.spawn();
////                            System.out.println(LocalTime.now());
//
//                        } else System.err.println("is alive = false!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                    }
//                }
//                cell.spawnPlants();
//            }
//        }
//    }
//
//    public void printState() {
//        getStatistic();
//
//    }
}