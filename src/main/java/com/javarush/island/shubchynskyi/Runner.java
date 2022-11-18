package com.javarush.island.shubchynskyi;

import com.javarush.island.shubchynskyi.entity.gamefield.GameField;
import com.javarush.island.shubchynskyi.services.GameWorker;
import com.javarush.island.shubchynskyi.view.ConsoleView;
import com.javarush.island.shubchynskyi.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runner {

    //через Lombok - плагин ломбок и зависимость, потом анотация над классом

    private static final Logger log = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        log.trace("Application start");
        log.info("Application start");
        log.error("Application start");
        log.warn("Application start");
        log.debug("Application start");
        GameField gamefield = new GameField();
        View viewer = new ConsoleView(gamefield);
        GameWorker gameWorker = new GameWorker(gamefield, viewer);
        gameWorker.run();

    }
}
