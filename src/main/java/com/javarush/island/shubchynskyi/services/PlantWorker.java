package com.javarush.island.shubchynskyi.services;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.entity.gamefield.GameField;
import com.javarush.island.shubchynskyi.entity.plants.Plant;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PlantWorker implements Runnable {


    private final Plant plant;
    private final GameField gameField;
    private final Queue<PlantTask> plantTasks = new ConcurrentLinkedQueue<>();

    public PlantWorker(GameField gameField, Plant animal) {
        this.gameField = gameField;
        this.plant = animal;
    }



    @Override
    public void run() {

        for (Cell[] cells : gameField.gameMap) {
            for (Cell cell : cells) {
                cell.getLock().lock();
                try {

                    if (cell.plantsInCell.get(plant.getAvatar()).size() > 0) {
                        cell.plantsInCell
                                .get(plant.getAvatar())
                                .forEach(o -> plantTasks.add(new PlantTask(o)));
                    }
                } finally {
                    cell.getLock().unlock();
                }
            }
        }
        //запускаем и отчищаем очередь
        for (PlantTask plantTask : plantTasks) {
            plantTask.startTask();
        }
        plantTasks.clear();

    }
}
