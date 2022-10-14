package com.javarush.island.shubchynskyi.preferences;

import jdk.jfr.Description;
import jdk.jfr.Name;

import java.lang.annotation.Target;

public class Preferences {

    private Preferences() {}

    /**
     * Constants
     */
    public static final String NAME = "Name";
    public static final String WEIGHT = "Weight";
    public static final String MAX_PER_CELL = "MaxPerCell";
    public static final String SPEED = "Speed";
    public static final String MAX_FOOD = "MaxFood";

    public final static String HERBIVORES_PACKAGE = "com.javarush.island.shubchynskyi.entity.animals.herbivores";
    public final static String OMNIVORES_PACKAGE = "com.javarush.island.shubchynskyi.entity.animals.omnivores";
    public final static String PREDATORS_PACKAGE = "com.javarush.island.shubchynskyi.entity.animals.predators";

    /** Enums for EntityFactory*/
    public enum AnimalEnum {
        BUFFALO, BEAR, HORSE, DEER, BOAR, SHEEP, GOAT, WOLF, SNAKE, FOX, EAGLE, RABBIT, DUCK, MOUSE, CATERPILLAR
    }


    /** Avatars */
    public final static String BUFFALO_AVATAR = "\uD83D\uDC03";
    public final static String BEAR_AVATAR = "\uD83D\uDC3B";
    public final static String HORSE_AVATAR = "\uD83D\uDC0E";
    public final static String DEER_AVATAR = "\uD83E\uDD8C";
    public final static String BOAR_AVATAR = "\uD83D\uDC17";
    public final static String SHEEP_AVATAR = "\uD83D\uDC11";
    public final static String GOAT_AVATAR = "\uD83D\uDC10";
    public final static String WOLF_AVATAR = "\uD83D\uDC3A";
    public final static String SNAKE_AVATAR = "\uD83D\uDC0D";
    public final static String FOX_AVATAR = "\uD83E\uDD8A";
    public final static String EAGLE_AVATAR = "\uD83E\uDD85";
    public final static String RABBIT_AVATAR = "\uD83D\uDC07";
    public final static String DUCK_AVATAR = "\uD83E\uDD86";
    public final static String MOUSE_AVATAR = "\uD83D\uDC01";
    public final static String CATERPILLAR_AVATAR = "\uD83D\uDC1B";

    /**
     * Island size
     */
    public static final int gameFieldWidth = 100;
    public static final int gameFieldHeight = 20;

    /** Plants */
    public static final float plantWeight = 1;
    public static final float plantMaxPerCell = 200;
    public static final int generationTimeSecond = 5;

    /** Aminals */
    public static final int predatorsTryingToEat = 3;

    /** Wolf */
    public static final String wolfName = "Wolf";
    public static final double wolfWeight = 50;
    public static final int wolfMaxPerCell = 30;
    public static final int wolfSpeed = 3;
    public static final double wolfMaxFood = 8;

    /** Boa */
    public static final String boaName = "Boa";
    public static final double boaWeight = 15;
    public static final int boaMaxPerCell = 30;
    public static final int boaSpeed = 1;
    public static final double boaMaxFood = 3;

    /** Fox */
    public static final String foxName = "Fox";
    public static final double foxWeight = 8;
    public static final int foxMaxPerCell = 30;
    public static final int foxSpeed = 2;
    public static final double foxMaxFood = 2;

    /** Bear */
    public static final String bearName = "Bear";
    public static final double bearWeight = 500;
    public static final int bearMaxPerCell = 5;
    public static final int bearSpeed = 2;
    public static final double bearMaxFood = 80;

    /** Eagle */
    public static final String eagleName = "Eagle";
    public static final double eagleWeight = 6;
    public static final int eagleMaxPerCell = 20;
    public static final int eagleSpeed = 3;
    public static final double eagleMaxFood = 1;

    /** Horse */
    public static final String horseName = "Horse";
    public static final double horseWeight = 400;
    public static final int horseMaxPerCell = 20;
    public static final int horseSpeed = 4;
    public static final double horseMaxFood = 60;

    /** Rabbit */
    public static final String rabbitName = "Rabbit";
    public static final double rabbitWeight = 2;
    public static final int rabbitMaxPerCell = 150;
    public static final int rabbitSpeed = 2;
    public static final double rabbitMaxFood = 0.45f;

    /** Mouse */
    public static final String mouseName = "Mouse";
    public static final double mouseWeight = 0.05f;
    public static final int mouseMaxPerCell = 500;
    public static final int mouseSpeed = 1;
    public static final double mouseMaxFood = 0.01f;

    /** Goat */
    public static final String goatName = "Goat";
    public static final double goatWeight = 60;
    public static final int goatMaxPerCell = 140;
    public static final int goatSpeed = 3;
    public static final double goatMaxFood = 10;

    /** Sheep */
    public static final String sheepName = "Sheep";
    public static final double sheepWeight = 70;
    public static final int sheepMaxPerCell = 140;
    public static final int sheepSpeed = 3;
    public static final double sheepMaxFood = 15;

    /** Boar */
    public static final String boarName = "Boar";
    public static final double boarWeight = 400;
    public static final int boarMaxPerCell = 50;
    public static final int boarSpeed = 2;
    public static final double boarMaxFood = 50;

    /** Buffalo */
    public static final String buffaloName = "Buffalo";
    public static final double buffaloWeight = 700;
    public static final int buffaloMaxPerCell = 10;
    public static final int buffaloSpeed = 3;
    public static final double buffaloMaxFood = 100;

    /** Duck */
    public static final String duckName = "Duck";
    public static final double duckWeight = 1;
    public static final int duckMaxPerCell = 200;
    public static final int duckSpeed = 4;
    public static final double duckMaxFood = 0.15f;

    /** Caterpillar */
    public static final String caterpillarName = "Caterpillar";
    public static final double caterpillarWeight = 0.01f;
    public static final int caterpillarMaxPerCell = 1000;
    public static final int caterpillarSpeed = 0;
    public static final double caterpillarMaxFood = 0;

    /** Deer */
    public static final String deerName = "Deer";
    public static final double deerWeight = 0.01d;
    public static final int deerMaxPerCell = 1000;
    public static final int deerSpeed = 0;
    public static final double deerMaxFood = 0;


}
