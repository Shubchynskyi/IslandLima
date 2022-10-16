package com.javarush.island.shubchynskyi.entity.animals;

import com.javarush.island.shubchynskyi.entity.plants.Plant;


public interface Herbivore {

    default void eat(Plant plant) {

    }

}
