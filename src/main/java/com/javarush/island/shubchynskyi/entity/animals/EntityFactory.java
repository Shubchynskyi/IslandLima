package com.javarush.island.shubchynskyi.entity.animals;

import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.preferences.Preferences;
import com.javarush.island.shubchynskyi.utils.Generator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class EntityFactory {

    public Set<Animal> prototypes = new HashSet<>();

    public Cell getFilledCell() {
        Cell resultCell = new Cell();
        for (Animal prototype : prototypes) {
            int randomInt = Generator.getRandom(0, prototype.getMaxPerCell());
            for (int i = 0; i <= randomInt; i++) {
                resultCell.animalsInCell.add(prototype.clone());
            }
        }
        return resultCell;
    }

    // TODO remove if not used
    public Animal getAnimal (Preferences.AnimalEnum animalEnum) {

        for (Animal prototype : prototypes) {
            if(prototype.getName().equalsIgnoreCase(animalEnum.toString())){
                return prototype.clone();
            }
        }

        //TODO убрать возможность возврата null
        return null;
    }

    //TODO перехватить исключения - заменить на свое
    public void fillPrototypes(String[] args) {
        for (String arg : args) {
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
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }


}
