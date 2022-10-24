package com.javarush.island.shubchynskyi.services;

import com.javarush.island.shubchynskyi.entity.animals.Organism;
import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.entity.gamefield.GameField;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class OrganismWorker implements Runnable {

    private final Organism organism;
    private final GameField gameField;
    private final Queue<OrganismTask> organismTasks = new ConcurrentLinkedQueue<>();

    public OrganismWorker(GameField gameField, Organism organism) {
        this.gameField = gameField;
        this.organism = organism;
    }

    @Override
    public void run() {

        for (Cell[] cells : gameField.getGameField()) {
            for (Cell cell : cells) {
                cell.getLock().lock();

                // TODO need refactor, remove one if-else after Organism refactoring
                try {
                    if(cell.getAnimalsInCell().get(organism.getAvatar()) != null){
                        if (cell.getAnimalsInCell().get(organism.getAvatar()).size() > 0) {
                            cell.getAnimalsInCell()
                                    .get(organism.getAvatar())
                                    .forEach(o -> organismTasks.add(new OrganismTask(o)));
                        }
                    } else

                    if (cell.getPlantsInCell().get(organism.getAvatar()).size() > 0) {

                        cell.getPlantsInCell()
                                .get(organism.getAvatar())
                                .forEach(o -> organismTasks.add(new OrganismTask(o)));
                    }
                } finally {
                    cell.getLock().unlock();
                }
            }
        }
        //запускаем и отчищаем очередь
        for (OrganismTask organismTask : organismTasks) {
            organismTask.startTask();
        }
        organismTasks.clear();

    }
}