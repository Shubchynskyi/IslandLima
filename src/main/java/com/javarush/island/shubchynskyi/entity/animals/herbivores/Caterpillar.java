package com.javarush.island.shubchynskyi.entity.animals.herbivores;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.animals.Herbivore;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import com.javarush.island.shubchynskyi.utils.Generator;

import java.util.Map;
import java.util.Set;

import static com.javarush.island.shubchynskyi.settings.EntitySettings.animalPrototypes;

public class Caterpillar extends Animal implements Herbivore {

    @Override
    public void spawn() {

        int maxToSpawn = 0;

        for (var var : getCurrentCell().plantsInCell.entrySet()) {
            maxToSpawn += var.getValue().size();
        }
        maxToSpawn = maxToSpawn*3;
        int freeSlot = getMaxPerCell() - getCurrentCell().animalsInCell.get(getAvatar()).size();
        if(maxToSpawn > freeSlot) {
            maxToSpawn = freeSlot;
        }
        if (maxToSpawn > 1) {
            int toSpawn = Generator.getRandom(0, maxToSpawn);

            for (Animal animalPrototype : animalPrototypes) {
                if (animalPrototype.getAvatar().equals(getAvatar())) {
                    for (int i = 0; i < toSpawn; i++) {
                        getCurrentCell().animalsInCell.get(getAvatar()).add(animalPrototype.clone(getCurrentCell()));
                    }
                }
            }
        }

    }


    @Override
    public void move() {

    }

    @Override
    public void weightLoss() {

    }

    @Override
    public void eat() {

    }
}
