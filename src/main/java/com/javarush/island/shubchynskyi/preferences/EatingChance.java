package com.javarush.island.shubchynskyi.preferences;

import com.javarush.island.shubchynskyi.preferences.AnimalPref.AnimalEnums;

public class EatingChance {
    private EatingChance() {
    }

    private static final int size = AnimalPref.prototypes.size();
    private static final int[][] eatingChance = new int[size][size];

    public static int[][] getEatingChance() {
        return eatingChance;
    }

    static {
        /* Herbivores */
        eatingChance[AnimalEnums.DUCK.ordinal()][AnimalEnums.CATERPILLAR.ordinal()] = 90;

        eatingChance[AnimalEnums.BOAR.ordinal()][AnimalEnums.MOUSE.ordinal()] = 50;
        eatingChance[AnimalEnums.BOAR.ordinal()][AnimalEnums.CATERPILLAR.ordinal()] = 90;

        eatingChance[AnimalEnums.MOUSE.ordinal()][AnimalEnums.CATERPILLAR.ordinal()] = 90;

        /* Predators */
        eatingChance[AnimalEnums.EAGLE.ordinal()][AnimalEnums.FOX.ordinal()] = 10;
        eatingChance[AnimalEnums.EAGLE.ordinal()][AnimalEnums.RABBIT.ordinal()] = 90;
        eatingChance[AnimalEnums.EAGLE.ordinal()][AnimalEnums.MOUSE.ordinal()] = 90;
        eatingChance[AnimalEnums.EAGLE.ordinal()][AnimalEnums.DUCK.ordinal()] = 80;

        eatingChance[AnimalEnums.BEAR.ordinal()][AnimalEnums.BOA.ordinal()] = 80;
        eatingChance[AnimalEnums.BEAR.ordinal()][AnimalEnums.HORSE.ordinal()] = 40;
        eatingChance[AnimalEnums.BEAR.ordinal()][AnimalEnums.DEER.ordinal()] = 80;
        eatingChance[AnimalEnums.BEAR.ordinal()][AnimalEnums.RABBIT.ordinal()] = 80;
        eatingChance[AnimalEnums.BEAR.ordinal()][AnimalEnums.MOUSE.ordinal()] = 90;
        eatingChance[AnimalEnums.BEAR.ordinal()][AnimalEnums.GOAT.ordinal()] = 70;
        eatingChance[AnimalEnums.BEAR.ordinal()][AnimalEnums.SHEEP.ordinal()] = 70;
        eatingChance[AnimalEnums.BEAR.ordinal()][AnimalEnums.BOAR.ordinal()] = 50;
        eatingChance[AnimalEnums.BEAR.ordinal()][AnimalEnums.BUFFALO.ordinal()] = 20;
        eatingChance[AnimalEnums.BEAR.ordinal()][AnimalEnums.DUCK.ordinal()] = 10;

        eatingChance[AnimalEnums.FOX.ordinal()][AnimalEnums.RABBIT.ordinal()] = 70;
        eatingChance[AnimalEnums.FOX.ordinal()][AnimalEnums.MOUSE.ordinal()] = 90;
        eatingChance[AnimalEnums.FOX.ordinal()][AnimalEnums.DUCK.ordinal()] = 60;
        eatingChance[AnimalEnums.FOX.ordinal()][AnimalEnums.CATERPILLAR.ordinal()] = 40;

        eatingChance[AnimalEnums.BOA.ordinal()][AnimalEnums.FOX.ordinal()] = 15;
        eatingChance[AnimalEnums.BOA.ordinal()][AnimalEnums.RABBIT.ordinal()] = 20;
        eatingChance[AnimalEnums.BOA.ordinal()][AnimalEnums.MOUSE.ordinal()] = 40;
        eatingChance[AnimalEnums.BOA.ordinal()][AnimalEnums.DUCK.ordinal()] = 10;

        eatingChance[AnimalEnums.WOLF.ordinal()][AnimalEnums.HORSE.ordinal()] = 10;
        eatingChance[AnimalEnums.WOLF.ordinal()][AnimalEnums.DEER.ordinal()] = 15;
        eatingChance[AnimalEnums.WOLF.ordinal()][AnimalEnums.RABBIT.ordinal()] = 60;
        eatingChance[AnimalEnums.WOLF.ordinal()][AnimalEnums.MOUSE.ordinal()] = 80;
        eatingChance[AnimalEnums.WOLF.ordinal()][AnimalEnums.GOAT.ordinal()] = 60;
        eatingChance[AnimalEnums.WOLF.ordinal()][AnimalEnums.SHEEP.ordinal()] = 70;
        eatingChance[AnimalEnums.WOLF.ordinal()][AnimalEnums.BOAR.ordinal()] = 15;
        eatingChance[AnimalEnums.WOLF.ordinal()][AnimalEnums.BUFFALO.ordinal()] = 10;
        eatingChance[AnimalEnums.WOLF.ordinal()][AnimalEnums.DUCK.ordinal()] = 40;
    }

}
