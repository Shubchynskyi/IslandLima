package com.javarush.island.shubchynskyi.services;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.entity.gamefield.GameField;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

// потоки для животных
public class AnimalWorker implements Runnable {
    
    private final Animal animal;
    private final GameField gameField;
    private final Queue<AnimalTask> animalTasks = new ConcurrentLinkedQueue<>();
    
    public AnimalWorker(GameField gameField, Animal animal) {
        this.gameField = gameField;
        this.animal = animal;
    }



    @Override
    public void run() {

        for (Cell[] cells : gameField.gameMap) {
            for (Cell cell : cells) {
                cell.getLock().lock();
                try {

                    if (cell.animalsInCell.get(animal.getAvatar()).size() > 0) {
                        cell.animalsInCell
                                .get(animal.getAvatar())
                                .forEach(o -> animalTasks.add(new AnimalTask(o)));
                    }
                } finally {
                    cell.getLock().unlock();
                }
            }
        }
        //запускаем и отчищаем очередь
        for (AnimalTask animalTask : animalTasks) {
            animalTask.startTask();
        }
        animalTasks.clear();

    }
}
