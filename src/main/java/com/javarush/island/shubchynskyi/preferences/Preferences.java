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
    public static final String AVATAR = "Avatar";

    /** Avatars */

//    public static String getAvatar(Enum<?> tEnum) {
//
//    }

    public final static String GRASS_AVATAR = "\uD83C\uDF3F";
    public final static String TREE_AVATAR = "\ud83c\udf32";










}
