package com.javarush.island.shubchynskyi.entity.gamefield;

import com.javarush.island.shubchynskyi.preferences.Preferences;


public class Gamefield {
    public final Cell[][] field = new Cell[Preferences.gameFieldWidth][Preferences.gameFieldHeight];

    //заселяем животными и растениями


    public Gamefield() {
        initialize();
        print();
    }

    private void initialize() {
        for (int x = 0; x < Preferences.gameFieldWidth; x++) {
            for (int y = 0; y < Preferences.gameFieldHeight; y++) {
                field[x][y] = new Cell(x,y);
            }
        }
    }

    private void print(){
        for (int y = 0; y < Preferences.gameFieldHeight; y++) {
            for (int x = 0; x < Preferences.gameFieldWidth; x++) {
                System.out.println(field[x][y].toString());
            }
        }

        for (int y = 0; y < Preferences.gameFieldHeight; y++) {
            for (int x = 0; x < Preferences.gameFieldWidth; x++) {
                System.out.print("*");
                if(x == Preferences.gameFieldWidth-1) {
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
