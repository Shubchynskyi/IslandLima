package com.javarush.island.shubchynskyi.entity.gamefield;

import com.javarush.island.shubchynskyi.entity.EntityFactory;
import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.preferences.AnimalPref;
import com.javarush.island.shubchynskyi.preferences.Preferences;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;


public class GameField {

    public final EntityFactory entityFactory;

    public final Cell[][] field = new Cell[Preferences.gameFieldWidth][Preferences.gameFieldHeight];



    public GameField(EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
        initialize();
        print();
    }

    //заселяем животными и растениями
    private void initialize() {
        for (int x = 0; x < Preferences.gameFieldWidth; x++) {
            for (int y = 0; y < Preferences.gameFieldHeight; y++) {
                field[x][y] = entityFactory.getFilledCell(x, y);
            }
        }
        // определяем соседние ячейки
        for (int x = 0; x < Preferences.gameFieldWidth; x++) {
            for (int y = 0; y < Preferences.gameFieldHeight; y++) {
                if ((y - 1) >= 0)
                    field[x][y].addNeighbour(field[x][y - 1]);
                if ((x + 1) <= Preferences.gameFieldWidth - 1)
                    field[x][y].addNeighbour(field[x + 1][y]);
                if ((y + 1) <= Preferences.gameFieldHeight - 1)
                    field[x][y].addNeighbour(field[x][y + 1]);
                if ((x - 1) >= 0)
                    field[x][y].addNeighbour(field[x - 1][y]);
            }
        }
    }

    //отрисовка поля
    private void print() {
////          печать содержимого ячейки
//        for (int y = 0; y < Preferences.gameFieldHeight; y++) {
//            for (int x = 0; x < Preferences.gameFieldWidth; x++) {
//                System.out.println(field[x][y].toString());
//            }
//        }


        for (int y = 0; y < Preferences.gameFieldHeight; y++) {
            for (int x = 0; x < Preferences.gameFieldWidth; x++) {
                // TODO при отрисовке вывести аватар животного которых больше, исключить гусениц
//                // тут получаю ENUM максимального животного, далее его нужно передать в метод и вернуть аватар
//                field[x][y].animalsInCell.entrySet().stream()
//                        .max((o1, o2) -> o2.getValue().size() - o1.getValue().size()).get().getKey();
//
//
                System.out.println("*");
                if (x == Preferences.gameFieldWidth - 1) {
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
        for (int y = 0; y < Preferences.gameFieldHeight; y++) {
            for (int x = 0; x < Preferences.gameFieldWidth; x++) {

                System.out.println(field[x][y].toString());
            }
        }
    }


}
