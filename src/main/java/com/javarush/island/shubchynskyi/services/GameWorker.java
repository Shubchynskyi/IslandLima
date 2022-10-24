package com.javarush.island.shubchynskyi.services;

import com.javarush.island.shubchynskyi.entity.gamefield.GameField;
import com.javarush.island.shubchynskyi.exception.IslandException;
import com.javarush.island.shubchynskyi.settings.GameSettings;
import com.javarush.island.shubchynskyi.view.View;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.javarush.island.shubchynskyi.settings.EntitySettings.animalPrototypes;
import static com.javarush.island.shubchynskyi.settings.EntitySettings.plantPrototypes;

public class GameWorker implements Runnable {

    private final GameField gameField;
    private final View viewer;
    private final int UPDATE_PERIOD = GameSettings.UPDATE_PERIOD;
    private final int PROCESSORS_COUNT = Runtime.getRuntime().availableProcessors();

    public GameWorker(GameField gameField, View viewer) {
        this.gameField = gameField;
        this.viewer = viewer;
    }

    @Override
    public void run() {
        // изначальная отрисовка поля и статистики
//        viewer.showMap();
        viewer.showStatistic();


        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(PROCESSORS_COUNT);

//        List<?> allWorkers;


        List<? extends Runnable> animalWorkers = animalPrototypes.stream()
                .map(o -> new AnimalWorker(gameField, o))
                .toList();

        List<PlantWorker> plantWorkers = plantPrototypes.stream()
                .map(o -> new PlantWorker(gameField, o))
                .toList();

//        allWorkers.addAll(animalWorkers)
//
//        animalWorkers.add(plantWorkers)

        threadPool.scheduleWithFixedDelay(() -> {
                    ExecutorService executorService = Executors.newFixedThreadPool(PROCESSORS_COUNT);
                    animalWorkers.forEach(executorService::submit);
                    plantWorkers.forEach(executorService::submit);
                    executorService.shutdown();
                    try {
                        if (executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                            viewer.showStatistic();
                        }
                    } catch (InterruptedException e) {
                        throw new IslandException(e);
                    }
                }
                , UPDATE_PERIOD, UPDATE_PERIOD, TimeUnit.MILLISECONDS);
    }

//    private void runWorkers(List<? extends Runnable> workers, View viewer) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (Runnable worker : workers) {
//            executorService.submit(worker);
//        }
//        executorService.shutdown();
//        try {
//            if (executorService.awaitTermination(1, TimeUnit.MINUTES)) {
////                viewer.showMap();
//                viewer.showStatistic();
//            }
//        } catch (InterruptedException e) {
//            throw new IslandException(e);
//        }
//
//
//    }


}
