package com.javarush.island.shubchynskyi.entity.gamefield;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import com.javarush.island.shubchynskyi.settings.GameSettings;
import com.javarush.island.shubchynskyi.utils.Generator;

import java.util.concurrent.CopyOnWriteArraySet;

import static com.javarush.island.shubchynskyi.settings.Prototypes.getAnimalPrototypes;
import static com.javarush.island.shubchynskyi.settings.Prototypes.getPlantPrototypes;

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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void initialize() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                gameField[x][y] = getFilledCell(x, y);
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if ((y - 1) >= 0) gameField[x][y].addNeighbour(gameField[x][y - 1]);
                if ((x + 1) <= width - 1) gameField[x][y].addNeighbour(gameField[x + 1][y]);
                if ((y + 1) <= height - 1) gameField[x][y].addNeighbour(gameField[x][y + 1]);
                if ((x - 1) >= 0) gameField[x][y].addNeighbour(gameField[x - 1][y]);
            }
        }
    }
//TODO вынести в фабрику? Заменить класс прототип на фабрика
    private Cell getFilledCell(int x, int y) {
        Cell resultCell = new Cell(x, y);

        for (Animal prototype : getAnimalPrototypes()) {
            resultCell.getAnimalsInCell().put(prototype.getAvatar(), new CopyOnWriteArraySet<>());
            int randomInt = Generator.getRandom(0, prototype.getMaxPerCell());
            for (int i = 0; i <= randomInt; i++) {
                resultCell.getAnimalsInCell().get(prototype.getAvatar()).add(prototype.clone(resultCell));
            }
        }

        for (Plant prototype : getPlantPrototypes()) {
            resultCell.getPlantsInCell().put(prototype.getAvatar(), new CopyOnWriteArraySet<>());
            int randomInt = Generator.getRandom(0, prototype.getMaxPerCell());
            for (int i = 0; i <= randomInt; i++) {
                resultCell.getPlantsInCell().get(prototype.getAvatar()).add(prototype.clone(resultCell));
            }
        }

        return resultCell;
    }
}