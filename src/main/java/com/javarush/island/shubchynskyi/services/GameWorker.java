package com.javarush.island.shubchynskyi.services;

import com.javarush.island.shubchynskyi.entity.gamefield.GameField;
import com.javarush.island.shubchynskyi.settings.GameSettings;
import com.javarush.island.shubchynskyi.view.View;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class GameWorker implements Runnable{

    private final GameField gameField;
    private final View viewer;
    private final int UPDATE_PERIOD = GameSettings.UPDATE_PERIOD;

    public GameWorker(GameField gameField, View viewer) {
        this.gameField = gameField;
        this.viewer = viewer;
    }





    private final int processorCount = Runtime.getRuntime().availableProcessors();

    ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(processorCount);





    @Override
    public void run() {

//        threadPool.schedule()
//        AnimalWorker animalWorker = new AnimalWorker(gameField,)

    }
}
