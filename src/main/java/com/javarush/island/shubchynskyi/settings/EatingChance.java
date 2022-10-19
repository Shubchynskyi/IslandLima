package com.javarush.island.shubchynskyi.settings;

import com.javarush.island.shubchynskyi.settings.EntitySettings.EntityEnums;

public class EatingChance {
    private EatingChance() {
    }

    private static final int size = EntitySettings.animalPrototypes.size();
    private static final int[][] eatingChance = new int[size][size];

    public static int[][] getEatingChance() {
        return eatingChance;
    }

    static {
        /* Herbivores */
        eatingChance[EntityEnums.DUCK.ordinal()][EntityEnums.CATERPILLAR.ordinal()] = 90;

        eatingChance[EntityEnums.BOAR.ordinal()][EntityEnums.MOUSE.ordinal()] = 50;
        eatingChance[EntityEnums.BOAR.ordinal()][EntityEnums.CATERPILLAR.ordinal()] = 90;

        eatingChance[EntityEnums.MOUSE.ordinal()][EntityEnums.CATERPILLAR.ordinal()] = 90;

        /* Predators */
        eatingChance[EntityEnums.EAGLE.ordinal()][EntityEnums.FOX.ordinal()] = 10;
        eatingChance[EntityEnums.EAGLE.ordinal()][EntityEnums.RABBIT.ordinal()] = 90;
        eatingChance[EntityEnums.EAGLE.ordinal()][EntityEnums.MOUSE.ordinal()] = 90;
        eatingChance[EntityEnums.EAGLE.ordinal()][EntityEnums.DUCK.ordinal()] = 80;

        eatingChance[EntityEnums.BEAR.ordinal()][EntityEnums.BOA.ordinal()] = 80;
        eatingChance[EntityEnums.BEAR.ordinal()][EntityEnums.HORSE.ordinal()] = 40;
        eatingChance[EntityEnums.BEAR.ordinal()][EntityEnums.DEER.ordinal()] = 80;
        eatingChance[EntityEnums.BEAR.ordinal()][EntityEnums.RABBIT.ordinal()] = 80;
        eatingChance[EntityEnums.BEAR.ordinal()][EntityEnums.MOUSE.ordinal()] = 90;
        eatingChance[EntityEnums.BEAR.ordinal()][EntityEnums.GOAT.ordinal()] = 70;
        eatingChance[EntityEnums.BEAR.ordinal()][EntityEnums.SHEEP.ordinal()] = 70;
        eatingChance[EntityEnums.BEAR.ordinal()][EntityEnums.BOAR.ordinal()] = 50;
        eatingChance[EntityEnums.BEAR.ordinal()][EntityEnums.BUFFALO.ordinal()] = 20;
        eatingChance[EntityEnums.BEAR.ordinal()][EntityEnums.DUCK.ordinal()] = 10;

        eatingChance[EntityEnums.FOX.ordinal()][EntityEnums.RABBIT.ordinal()] = 70;
        eatingChance[EntityEnums.FOX.ordinal()][EntityEnums.MOUSE.ordinal()] = 90;
        eatingChance[EntityEnums.FOX.ordinal()][EntityEnums.DUCK.ordinal()] = 60;
        eatingChance[EntityEnums.FOX.ordinal()][EntityEnums.CATERPILLAR.ordinal()] = 40;

        eatingChance[EntityEnums.BOA.ordinal()][EntityEnums.FOX.ordinal()] = 15;
        eatingChance[EntityEnums.BOA.ordinal()][EntityEnums.RABBIT.ordinal()] = 20;
        eatingChance[EntityEnums.BOA.ordinal()][EntityEnums.MOUSE.ordinal()] = 40;
        eatingChance[EntityEnums.BOA.ordinal()][EntityEnums.DUCK.ordinal()] = 10;

        eatingChance[EntityEnums.WOLF.ordinal()][EntityEnums.HORSE.ordinal()] = 10;
        eatingChance[EntityEnums.WOLF.ordinal()][EntityEnums.DEER.ordinal()] = 15;
        eatingChance[EntityEnums.WOLF.ordinal()][EntityEnums.RABBIT.ordinal()] = 60;
        eatingChance[EntityEnums.WOLF.ordinal()][EntityEnums.MOUSE.ordinal()] = 80;
        eatingChance[EntityEnums.WOLF.ordinal()][EntityEnums.GOAT.ordinal()] = 60;
        eatingChance[EntityEnums.WOLF.ordinal()][EntityEnums.SHEEP.ordinal()] = 70;
        eatingChance[EntityEnums.WOLF.ordinal()][EntityEnums.BOAR.ordinal()] = 15;
        eatingChance[EntityEnums.WOLF.ordinal()][EntityEnums.BUFFALO.ordinal()] = 10;
        eatingChance[EntityEnums.WOLF.ordinal()][EntityEnums.DUCK.ordinal()] = 40;
    }

}
