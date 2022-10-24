package com.javarush.island.shubchynskyi.settings;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import com.javarush.island.shubchynskyi.exception.IslandException;

import static com.javarush.island.shubchynskyi.settings.EntitySettings.*;

import java.io.*;
import java.util.*;
import java.lang.reflect.InvocationTargetException;

public class Prototypes {
    private Prototypes() {}

    private static final Set<Animal> animalPrototypes = new HashSet<>();
    private static final Set<Plant> plantPrototypes = new HashSet<>();

    public static Set<Animal> getAnimalPrototypes() {
        return animalPrototypes;
    }
    public static Set<Plant> getPlantPrototypes() {
        return plantPrototypes;
    }

    static {
        initPrototypes();
    }

    private static void initPrototypes() {
        fillPrototypes(animalPrototypes, ANIMAL_PACKAGES);
        fillPrototypes(plantPrototypes, PLANT_PACKAGES);

        for (Animal prototype : animalPrototypes) {
            prototype.setChancesToEat(EatingChance.fillAnimalChanceToEat(prototype));
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> void fillPrototypes(Set<T> prototypeSet, String... packageWithEntityClasses) {
        for (String arg : packageWithEntityClasses) {
            InputStream stream = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(arg.replaceAll("[.]", "/"));
            if (stream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                List<String> collect = reader.lines().map(s -> (s.substring(0, s.length() - 6))).toList();
                for (String s : collect) {
                    try {
                        prototypeSet.add((T) Class.forName(arg + "." + s).getConstructor().newInstance());
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException | ClassNotFoundException e) {
                        throw new IslandException(e);
                    }
                }
            }
        }
    }
}
