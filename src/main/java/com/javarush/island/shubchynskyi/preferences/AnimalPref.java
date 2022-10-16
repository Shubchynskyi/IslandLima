package com.javarush.island.shubchynskyi.preferences;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.exception.IslandException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnimalPref {
    private AnimalPref() {}

    public static Set<Animal> prototypes = new HashSet<>();

    /** Packages for animals prototype */
    private static final String HERBIVORES_PACKAGE = "com.javarush.island.shubchynskyi.entity.animals.herbivores";
    private static final String OMNIVORES_PACKAGE = "com.javarush.island.shubchynskyi.entity.animals.omnivores";
    private static final String PREDATORS_PACKAGE = "com.javarush.island.shubchynskyi.entity.animals.predators";
    private static final String[] ANIMAL_PACKAGES = new String[] {HERBIVORES_PACKAGE, OMNIVORES_PACKAGE, PREDATORS_PACKAGE};

    /** Animal Enums */
    public enum AnimalEnums {
        WOLF, BOA, FOX, BEAR, EAGLE,
        HORSE, DEER, RABBIT, MOUSE, GOAT, SHEEP, BOAR, BUFFALO, DUCK, CATERPILLAR
    }

    // TODO remove if not used
    public static final int predatorsTryingToEat = 3;


    /** Wolf */
    public static final String wolfName = "Wolf";
    public static final AnimalEnums wolfType = AnimalEnums.WOLF;
    public static final double wolfWeight = 50;
    public static final int wolfMaxPerCell = 30;
    public static final int wolfSpeed = 3;
    public static final double wolfMaxFood = 8;

    /** Boa */
    public static final String boaName = "Boa";
    public static final AnimalEnums boaType = AnimalEnums.BOA;
    public static final double boaWeight = 15;
    public static final int boaMaxPerCell = 30;
    public static final int boaSpeed = 1;
    public static final double boaMaxFood = 3;

    /** Fox */
    public static final String foxName = "Fox";
    public static final AnimalEnums foxType = AnimalEnums.FOX;
    public static final double foxWeight = 8;
    public static final int foxMaxPerCell = 30;
    public static final int foxSpeed = 2;
    public static final double foxMaxFood = 2;

    /** Bear */
    public static final String bearName = "Bear";
    public static final AnimalEnums bearType = AnimalEnums.BEAR;
    public static final double bearWeight = 500;
    public static final int bearMaxPerCell = 5;
    public static final int bearSpeed = 2;
    public static final double bearMaxFood = 80;

    /** Eagle */
    public static final String eagleName = "Eagle";
    public static final AnimalEnums eagleType = AnimalEnums.EAGLE;
    public static final double eagleWeight = 6;
    public static final int eagleMaxPerCell = 20;
    public static final int eagleSpeed = 3;
    public static final double eagleMaxFood = 1;

    /** Horse */
    public static final String horseName = "Horse";
    public static final AnimalEnums horseType = AnimalEnums.HORSE;
    public static final double horseWeight = 400;
    public static final int horseMaxPerCell = 20;
    public static final int horseSpeed = 4;
    public static final double horseMaxFood = 60;

    /** Rabbit */
    public static final String rabbitName = "Rabbit";
    public static final AnimalEnums rabbitType = AnimalEnums.RABBIT;
    public static final double rabbitWeight = 2;
    public static final int rabbitMaxPerCell = 150;
    public static final int rabbitSpeed = 2;
    public static final double rabbitMaxFood = 0.45d;

    /** Mouse */
    public static final String mouseName = "Mouse";
    public static final AnimalEnums mouseType = AnimalEnums.MOUSE;
    public static final double mouseWeight = 0.05d;
    public static final int mouseMaxPerCell = 500;
    public static final int mouseSpeed = 1;
    public static final double mouseMaxFood = 0.01d;

    /** Goat */
    public static final String goatName = "Goat";
    public static final AnimalEnums goatType = AnimalEnums.GOAT;
    public static final double goatWeight = 60;
    public static final int goatMaxPerCell = 140;
    public static final int goatSpeed = 3;
    public static final double goatMaxFood = 10;

    /** Sheep */
    public static final String sheepName = "Sheep";
    public static final AnimalEnums sheepType = AnimalEnums.SHEEP;
    public static final double sheepWeight = 70;
    public static final int sheepMaxPerCell = 140;
    public static final int sheepSpeed = 3;
    public static final double sheepMaxFood = 15;

    /** Boar */
    public static final String boarName = "Boar";
    public static final AnimalEnums boarType = AnimalEnums.BOAR;
    public static final double boarWeight = 400;
    public static final int boarMaxPerCell = 50;
    public static final int boarSpeed = 2;
    public static final double boarMaxFood = 50;

    /** Buffalo */
    public static final String buffaloName = "Buffalo";
    public static final AnimalEnums buffaloType = AnimalEnums.BUFFALO;
    public static final double buffaloWeight = 700;
    public static final int buffaloMaxPerCell = 10;
    public static final int buffaloSpeed = 3;
    public static final double buffaloMaxFood = 100;

    /** Duck */
    public static final String duckName = "Duck";
    public static final AnimalEnums duckType = AnimalEnums.DUCK;
    public static final double duckWeight = 1;
    public static final int duckMaxPerCell = 200;
    public static final int duckSpeed = 4;
    public static final double duckMaxFood = 0.15d;

    /** Caterpillar */
    public static final String caterpillarName = "Caterpillar";
    public static final AnimalEnums caterpillarType = AnimalEnums.CATERPILLAR;
    public static final double caterpillarWeight = 0.01d;
    public static final int caterpillarMaxPerCell = 1000;
    public static final int caterpillarSpeed = 0;
    public static final double caterpillarMaxFood = 0;

    /** Deer */
    public static final String deerName = "Deer";
    public static final AnimalEnums deerType = AnimalEnums.DEER;
    public static final double deerWeight = 0.01d;
    public static final int deerMaxPerCell = 20;
    public static final int deerSpeed = 0;
    public static final double deerMaxFood = 0;



    //TODO перехватить исключения - заменить на свое
    static {
        for (String arg : ANIMAL_PACKAGES) {
            InputStream stream = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(arg.replaceAll("[.]", "/"));
            if (stream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                List<String> collect = reader.lines().map(s -> (s.substring(0, s.length() - 6))).toList();
                for (String s : collect) {
                    try {
                        prototypes.add((Animal) Class.forName(arg + "." + s).getConstructor().newInstance());
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException | ClassNotFoundException e) {
                        throw new IslandException(e);
                    }
                }
            }
        }
    }
}
