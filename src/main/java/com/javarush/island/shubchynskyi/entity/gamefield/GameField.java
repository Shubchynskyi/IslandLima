package com.javarush.island.shubchynskyi.entity.gamefield;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import com.javarush.island.shubchynskyi.settings.GameSettings;
import com.javarush.island.shubchynskyi.utils.Generator;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.javarush.island.shubchynskyi.settings.EntitySettings.animalPrototypes;
import static com.javarush.island.shubchynskyi.settings.EntitySettings.plantPrototypes;


public class GameField {

    private final int width = GameSettings.GAME_FIELD_WIDTH;
    private final int height = GameSettings.GAME_FIELD_HEIGHT;
    private final Cell[][] gameField = new Cell[width][height];

    public GameField() {
        initialize();
    }

    public Cell[][] getGameField() {
        return gameField;
    }

    public int getWidth() { return width; }
    public int getHeight() {return height; }

    private void initialize() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                gameField[x][y] = getFilledCell(x, y);
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

    private Cell getFilledCell(int x, int y) {
        Cell resultCell = new Cell(x, y);

        for (Animal prototype : animalPrototypes) {
            resultCell.animalsInCell.put(prototype.getAvatar(), new CopyOnWriteArraySet<>());
            int randomInt = Generator.getRandom(0, prototype.getMaxPerCell());
            for (int i = 0; i <= randomInt; i++) {
                resultCell.animalsInCell.get(prototype.getAvatar()).add(prototype.clone(resultCell));
            }
        }

        for (Plant prototype : plantPrototypes) {
            resultCell.plantsInCell.put(prototype.getAvatar(), new CopyOnWriteArraySet<>());
            int randomInt = Generator.getRandom(0, prototype.getMaxPerCell());
            for (int i = 0; i <= randomInt; i++) {
                resultCell.plantsInCell.get(prototype.getAvatar()).add(prototype.clone(resultCell));
            }
        }

        return resultCell;
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