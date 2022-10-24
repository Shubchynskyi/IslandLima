package com.javarush.island.shubchynskyi.entity.animals.herbivores;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.animals.Herbivore;
import com.javarush.island.shubchynskyi.utils.Generator;

import static com.javarush.island.shubchynskyi.settings.Prototypes.getAnimalPrototypes;

public class Caterpillar extends Animal implements Herbivore {

    @Override
    public void spawn() {


        // TODO завязано на растениях, не рождаются до максимума, надо фиксить
        int maxToSpawn = 0;

        for (var var : getCurrentCell().getPlantsInCell().entrySet()) {
            maxToSpawn += var.getValue().size();
        }
        maxToSpawn = maxToSpawn * 3;
        int freeSlot = getMaxPerCell() - getCurrentCell().getAnimalsInCell().get(getAvatar()).size();
        if (maxToSpawn > freeSlot) {
            maxToSpawn = freeSlot;
        }
        if (maxToSpawn > 1) {
            int toSpawn = Generator.getRandom(0, maxToSpawn);

            for (Animal animalPrototype : getAnimalPrototypes()) {
                if (animalPrototype.getAvatar().equals(getAvatar())) {
                    for (int i = 0; i < toSpawn; i++) {
                        getCurrentCell().getAnimalsInCell().get(getAvatar()).add(animalPrototype.clone(getCurrentCell()));
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
