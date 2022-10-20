package com.javarush.island.shubchynskyi.entity.animals;

import com.javarush.island.shubchynskyi.entity.plants.Plant;

import java.util.Set;


public interface Herbivore {

    default void eat(Plant plant) {

    }

}
