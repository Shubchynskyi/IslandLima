package com.javarush.island.shubchynskyi.services;

import com.javarush.island.shubchynskyi.entity.gamefield.GameField;
import com.javarush.island.shubchynskyi.exception.IslandException;
import com.javarush.island.shubchynskyi.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.javarush.island.shubchynskyi.entity.EntityFactory.getAnimalPrototypes;
import static com.javarush.island.shubchynskyi.entity.EntityFactory.getPlantPrototypes;
import static com.javarush.island.shubchynskyi.settings.GameSettings.UPDATE_PERIOD;

public class GameWorker implements Runnable {

    private final GameField gameField;
    private final View viewer;
    private final int PROCESSORS_COUNT = Runtime.getRuntime().availableProcessors();

    public GameWorker(GameField gameField, View viewer) {
        this.gameField = gameField;
        this.viewer = viewer;
    }

    @Override
    public void run() {
        viewer.showMap();
        viewer.showStatistic();

        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(PROCESSORS_COUNT);

        List<OrganismWorker> organismWorkers = new ArrayList<>();

        organismWorkers.addAll(getAnimalPrototypes().stream()
                .map(o -> new OrganismWorker(gameField, o))
                .toList());
        organismWorkers.addAll(getPlantPrototypes().stream()
                .map(o -> new OrganismWorker(gameField, o))
                .toList());

        threadPool.scheduleWithFixedDelay(() -> runWorkers(organismWorkers),
                UPDATE_PERIOD, UPDATE_PERIOD, TimeUnit.MILLISECONDS);
    }

    private void runWorkers(List<OrganismWorker> organismWorkers) {
        ExecutorService executorService = Executors.newFixedThreadPool(PROCESSORS_COUNT);
        organismWorkers.forEach(executorService::submit);
        executorService.shutdown();
        try {
            if (executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                viewer.showMap();
                viewer.showStatistic();
            }
        } catch (InterruptedException e) {
            throw new IslandException(e);
        }
    }
}
