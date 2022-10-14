package com.javarush.island.shubchynskyi;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.animals.EntityFactory;
import com.javarush.island.shubchynskyi.preferences.Preferences;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static com.javarush.island.shubchynskyi.preferences.Preferences.AnimalEnum.*;


public class Runner {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {

        EntityFactory entityFactory = new EntityFactory();
        entityFactory.fillPrototypes(
                Preferences.HERBIVORES_PACKAGE,
                Preferences.OMNIVORES_PACKAGE,
                Preferences.PREDATORS_PACKAGE
        );

        for (Animal prototype : entityFactory.prototypes) {
            System.out.println(prototype.getName());
        }

        Animal horse = entityFactory.getAnimal(HORSE);

        System.out.println();
        System.out.println(horse.getName());
        System.out.println(horse.getMaxFood());
        System.out.println(horse.getMaxPerCell());
        System.out.println(horse.getSpeed());
        System.out.println(horse.getWeight());


    }
}
