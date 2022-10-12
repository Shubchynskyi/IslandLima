package com.javarush.island.shubchynskyi.entity.animals;

import com.javarush.island.shubchynskyi.entity.plants.Plants;

public interface Herbivore {

    default void eat(Plants plant) {

    }

}
