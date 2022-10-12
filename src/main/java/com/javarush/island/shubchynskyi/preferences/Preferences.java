package com.javarush.island.shubchynskyi.preferences;

import jdk.jfr.Description;
import jdk.jfr.Name;

import java.lang.annotation.Target;

public class Preferences {

    private Preferences() {}

    /**
     * Constants
     */
    public static final String WEIGHT = "Weight";
    public static final String MAX_PER_CELL = "MaxPerCell";
    public static final String SPEED = "Speed";
    public static final String MAX_FOOD = "MaxFood";


    // TODO вынести аватары в конструкторы
     public String BUFFALO_AVATAR = "\uD83D\uDC03";
     public String BEAR_AVATAR = "\uD83D\uDC3B";
     public String HORSE_AVATAR = "\uD83D\uDC0E";
     public String DEER_AVATAR = "\uD83E\uDD8C";
     public String BOAR_AVATAR = "\uD83D\uDC17";
     public String SHEEP_AVATAR = "\uD83D\uDC11";
     public String GOAT_AVATAR = "\uD83D\uDC10";
     public String WOLF_AVATAR = "\uD83D\uDC3A";
     public String SNAKE_AVATAR = "\uD83D\uDC0D";
     public String FOX_AVATAR = "\uD83E\uDD8A";
     public String EAGLE_AVATAR = "\uD83E\uDD85";
     public String RABBIT_AVATAR = "\uD83D\uDC07";
     public String DUCK_AVATAR = "\uD83E\uDD86";
     public String MOUSE_AVATAR = "\uD83D\uDC01";
     public String CATERPILLAR_AVATAR = "\uD83D\uDC1B";

    /**
     * Island size
     */
    public static final int gameFieldWidth = 100;
    public static final int gameFieldHeight = 20;

    /** Plants */
    public static final float plantWeight = 1;
    public static final float plantMaxPerCell = 200;
    public static final int generationTimeSecond = 5;

    /** Wolf */
    public static final float wolfWeight = 50;
    public static final float wolfMaxPerCell = 30;
    public static final float wolfSpeed = 3;
    public static final float wolfMaxFood = 8;


    /** Boa */
    public static final float boaWeight = 15;
    public static final float boaMaxPerCell = 30;
    public static final float boaSpeed = 1;
    public static final float boaMaxFood = 3;

    /** Fox */
    public static final float foxWeight = 8;
    public static final float foxMaxPerCell = 30;
    public static final float foxSpeed = 2;
    public static final float foxMaxFood = 2;

    /** Bear */
    public static final float bearWeight = 500;
    public static final float bearMaxPerCell = 5;
    public static final float bearSpeed = 2;
    public static final float bearMaxFood = 80;

    /** Eagle */
    public static final float eagleWeight = 6;
    public static final float eagleMaxPerCell = 20;
    public static final float eagleSpeed = 3;
    public static final float eagleMaxFood = 1;

    /** Horse */
    public static final float horseWeight = 400;
    public static final float horseMaxPerCell = 20;
    public static final float horseSpeed = 4;
    public static final float horseMaxFood = 60;

    /** Rabbit */
    public static final float rabbitWeight = 2;
    public static final float rabbitMaxPerCell = 150;
    public static final float rabbitSpeed = 2;
    public static final float rabbitMaxFood = 0.45f;

    /** Mouse */
    public static final float mouseWeight = 0.05f;
    public static final float mouseMaxPerCell = 500;
    public static final float mouseSpeed = 1;
    public static final float mouseMaxFood = 0.01f;

    /** Goat */
    public static final float goatWeight = 60;
    public static final float goatMaxPerCell = 140;
    public static final float goatSpeed = 3;
    public static final float goatMaxFood = 10;

    /** Sheep */
    public static final float sheepWeight = 70;
    public static final float sheepMaxPerCell = 140;
    public static final float sheepSpeed = 3;
    public static final float sheepMaxFood = 15;

    /** Boar */
    public static final float boarWeight = 400;
    public static final float boarMaxPerCell = 50;
    public static final float boarSpeed = 2;
    public static final float boarMaxFood = 50;

    /** Buffalo */
    public static final float buffaloWeight = 700;
    public static final float buffaloMaxPerCell = 10;
    public static final float buffaloSpeed = 3;
    public static final float buffaloMaxFood = 100;

    /** Duck */
    public static final float duckWeight = 1;
    public static final float duckMaxPerCell = 200;
    public static final float duckSpeed = 4;
    public static final float duckMaxFood = 0.15f;

    /** Caterpillar */
    public static final float caterpillarWeight = 0.01f;
    public static final float caterpillarMaxPerCell = 1000;
    public static final float caterpillarSpeed = 0;
    public static final float caterpillarMaxFood = 0;




}
