package com.javarush.island.shubchynskyi.preferences;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import com.javarush.island.shubchynskyi.exception.IslandException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlantPref {
    private PlantPref(){}

    public static Set<Plant> prototypes = new HashSet<>();

    /** Packages for plants prototype */
    private static final String EVERGREEN_PACKAGE = "com.javarush.island.shubchynskyi.entity.plants.evergreen";
    private static final String[] PLANT_PACKAGES = new String[] {EVERGREEN_PACKAGE};

    /** Plant Enums */
    public enum PlantEnums {
        GRASS, TREE
    }

    /** GRASS */
    public static final String grassName = "Grass";
    public static final PlantEnums grassType = PlantEnums.GRASS;
    public static final double grassWeight = 1;
    public static final int grassMaxPerCell = 200;
    public static final int GRASS_GROWTH_TIME = 5;

    /** TREE */
    public static final String treeName = "Tree";
    public static final PlantEnums treeType = PlantEnums.TREE;
    public static final double treeWeight = 20;
    public static final int treeMaxPerCell = 50;
    public static final int TREE_GROWTH_TIME = 50;


    //TODO перехватить исключения - заменить на свое
    static {
        for (String arg : PLANT_PACKAGES) {
            InputStream stream = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(arg.replaceAll("[.]", "/"));
            if (stream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                List<String> collect = reader.lines().map(s -> (s.substring(0, s.length() - 6))).toList();
                for (String s : collect) {
                    try {
                        prototypes.add((Plant) Class.forName(arg + "." + s).getConstructor().newInstance());
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException | ClassNotFoundException e) {
                        throw new IslandException(e);
                    }
                }
            }
        }
    }








}
