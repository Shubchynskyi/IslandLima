package com.javarush.island.shubchynskyi.entity.gamefield;

import com.javarush.island.shubchynskyi.entity.EntityFactory;
import com.javarush.island.shubchynskyi.preferences.Preferences;


public class GameField {

    public final EntityFactory entityFactory;

    public final Cell[][] field = new Cell[Preferences.gameFieldWidth][Preferences.gameFieldHeight];

    //заселяем животными и растениями

    public GameField(EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
        initialize();
        print();
    }

    private void initialize() {
        for (int x = 0; x < Preferences.gameFieldWidth; x++) {
            for (int y = 0; y < Preferences.gameFieldHeight; y++) {
                field[x][y] = entityFactory.getFilledCell(x, y);
            }
        }
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

    private void print() {
////          печать содержимого ячейки
//        for (int y = 0; y < Preferences.gameFieldHeight; y++) {
//            for (int x = 0; x < Preferences.gameFieldWidth; x++) {
//                System.out.println(field[x][y].toString());
//            }
//        }

        for (int y = 0; y < Preferences.gameFieldHeight; y++) {
            for (int x = 0; x < Preferences.gameFieldWidth; x++) {
                
                if (x == Preferences.gameFieldWidth - 1) {
                    System.out.println();
                }
            }
        }

    }

    //делаем шаг
    public void makeStep() {

    }

    //вывод статистики
    public void printState() {

    }


}
