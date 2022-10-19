package com.javarush.island.shubchynskyi.entity.gamefield;

import com.javarush.island.shubchynskyi.entity.EntityFactory;
import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import com.javarush.island.shubchynskyi.settings.EntitySettings;
import com.javarush.island.shubchynskyi.settings.GameSettings;

import java.util.HashMap;
import java.util.Map;


public class GameField {

    private final int width = GameSettings.GAME_FIELD_WIDTH;
    private final int height = GameSettings.GAME_FIELD_HEIGHT;

    public GameField(EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
        statisticMapInit();
        initialize();
        print();
    }

    public final EntityFactory entityFactory;

    public final Cell[][] gameField = new Cell[width][height];

    private final Map<String, Integer> statisticMap = new HashMap<>();

    private void statisticMapInit() {
        for (Animal prototype : EntitySettings.animalPrototypes) {
            statisticMap.put(prototype.getAvatar(), 0);
        }
        for (Plant prototype : EntitySettings.plantPrototypes) {
            statisticMap.put(prototype.getAvatar(), 0);
        }
    }
    // вывод статистики по всей карте - нужен в конце хода
    // добавить в мапу ВСЕХ по аватару, потом вынести в стрингбилдер

    public String getStatistic() {

        StringBuilder result = new StringBuilder();

        for (Cell[] cells : gameField) {
            for (Cell cell : cells) {

                for (var var : cell.animalsInCell.entrySet()) {

//                    statisticMap.

                    int newValue = statisticMap.get(var.getKey()) + var.getValue().size();
                    statisticMap.put(var.getKey(),newValue);
                }

                for (var var : cell.plantsInCell.entrySet()) {
                    int newValue = statisticMap.get(var.getKey()) + var.getValue().size();
                    statisticMap.put(var.getKey(),newValue);
                }

                for (var var : statisticMap.entrySet()) {
                    result.append(var.getKey()).append(" = ").append(var.getValue()).append("; ");
                }
            }
        }

        return result.toString();
    }


    //заселяем животными и растениями
    private void initialize() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                gameField[x][y] = entityFactory.getFilledCell(x, y);
            }
        }
        // определяем соседние ячейки
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if ((y - 1) >= 0)
                    gameField[x][y].addNeighbour(gameField[x][y - 1]);
                if ((x + 1) <= width - 1)
                    gameField[x][y].addNeighbour(gameField[x + 1][y]);
                if ((y + 1) <= height - 1)
                    gameField[x][y].addNeighbour(gameField[x][y + 1]);
                if ((x - 1) >= 0)
                    gameField[x][y].addNeighbour(gameField[x - 1][y]);
            }
        }
    }

    //отрисовка поля
    private void print() {

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // TODO при отрисовке вывести аватар животного которых больше, исключить гусениц
//                // тут получаю ENUM максимального животного, далее его нужно передать в метод и вернуть аватар
//                field[x][y].animalsInCell.entrySet().stream()
//                        .max((o1, o2) -> o2.getValue().size() - o1.getValue().size()).get().getKey();
//
//
                System.out.print("*");
                if (x == width - 1) {
                    System.out.println();
                }
            }
        }

    }

    // создаем пул потоков

    //делаем шаг
    public void makeStep() {

    }

    //вывод статистики
    public void printState() {

        System.out.println(getStatistic());

        //статистика по каждой ячейке
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//
//                System.out.println(gameField[x][y].toString());
//
//
//            }
//        }
    }


}
