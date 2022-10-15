package com.javarush.island.shubchynskyi;

import com.javarush.island.shubchynskyi.entity.animals.EntityFactory;
import com.javarush.island.shubchynskyi.entity.gamefield.Gamefield;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;




public class Runner {
    public static void main(String[] args) {
        Gamefield gamefield = new Gamefield(new EntityFactory());


//        for (Animal prototype : entityFactory.prototypes) {
//            System.out.println(prototype.getName());
//        }
//
//        Animal horse = entityFactory.getAnimal(HORSE);
//
//        System.out.println();
//        System.out.println(horse.getName());
//        System.out.println(horse.getMaxFood());
//        System.out.println(horse.getMaxPerCell());
//        System.out.println(horse.getSpeed());
//        System.out.println(horse.getWeight());


    }
}
