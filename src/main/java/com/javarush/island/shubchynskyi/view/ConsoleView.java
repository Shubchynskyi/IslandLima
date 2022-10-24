package com.javarush.island.shubchynskyi.view;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.entity.gamefield.GameField;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static com.javarush.island.shubchynskyi.settings.Prototypes.*;

public class ConsoleView implements View {

    private final GameField gameField;
    private final Map<String, Integer> statisticMap = new HashMap<>();

    public ConsoleView(GameField gameField) {
        this.gameField = gameField;
    }



    @Override
    public void showMap() {

        for (int y = 0; y < gameField.getHeight(); y++) {
            for (int x = 0; x < gameField.getWidth(); x++) {
                // TODO при отрисовке вывести аватар животного которых больше, исключить гусениц
                System.out.print("*");
                if (x == gameField.getWidth() - 1) {
                    System.out.println();
                }
            }
        }


    }

    @Override
    public void showStatistic() {
        System.out.println(LocalTime.now());
        System.out.println(getStatistic());
    }

    @Override
    public void gameOver() {
        // TODO logic проверка на оставшихся животных, если умерли все, или если остались только мыши и гусеницы
    }

    //TODO при нуле выделить красным или удалить, так же можно если все красные, то GameOver
    public String getStatistic() {
        StringBuilder result = new StringBuilder();

        statisticMapInit();

        int oldValue;
        int newValue;
        String type;

        for (Cell[] cells : gameField.getGameField()) {
            for (Cell cell : cells) {
                for (var var : cell.getAnimalsInCell().entrySet()) {
                    type = var.getKey(); // получил ключ
                    newValue = var.getValue().size(); // размер сета по ключу map
                    oldValue = statisticMap.get(type);
                    statisticMap.put(type, oldValue + newValue);
                }
                for (var var : cell.getPlantsInCell().entrySet()) {
                    type = var.getKey(); // получил ключ
                    newValue = var.getValue().size(); // размер сета по ключу map
                    oldValue = statisticMap.get(type);
                    statisticMap.put(type, oldValue + newValue);
                }
            }
        }

        for (var var : statisticMap.entrySet()) {
            result.append(var.getKey()).append(" = ").append(var.getValue()).append("; ");
        }

        return result.toString();
    }

    // clear maps
    private void statisticMapInit() {
        for (Animal prototype : getAnimalPrototypes()) {
            statisticMap.put(prototype.getAvatar(), 0);
        }
        for (Plant prototype : getPlantPrototypes()) {
            statisticMap.put(prototype.getAvatar(), 0);
        }
    }
}
