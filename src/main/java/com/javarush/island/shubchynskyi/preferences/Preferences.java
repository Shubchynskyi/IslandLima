package com.javarush.island.shubchynskyi.preferences;

import jdk.jfr.Description;
import jdk.jfr.Name;

import java.lang.annotation.Target;
import java.util.Arrays;

public class Preferences {

    private Preferences() {}

    /** Island size */
    public static final int gameFieldWidth = 50;
    public static final int gameFieldHeight = 10;

    /** Constant */
    public static final String NAME = "Name";
    public static final String TYPE = "Type";
    public static final String WEIGHT = "Weight";
    public static final String MAX_PER_CELL = "MaxPerCell";
    public static final String SPEED = "Speed";
    public static final String MAX_FOOD = "MaxFood";

    /** Avatars */
    public final static String GRASS_AVATAR = "\uD83C\uDF3F";
    public final static String TREE_AVATAR = "\ud83c\udf32";

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








}
