package com.javarush.island.shubchynskyi.entity.animals;

import com.javarush.island.shubchynskyi.preferences.Preferences;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class EntityFactory {

    public Set<Animal> prototypes = new HashSet<>();

    //TODO перехватить исключения
    public void fillPrototypes(String... args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IOException {
        for (String arg : args) {
            InputStream stream = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(arg.replaceAll("[.]", "/"));
            if (stream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                List<String> collect = reader.lines().map(s -> (s.substring(0, s.length() - 6))).toList();
                for (String s : collect) {
                    prototypes.add((Animal) Class.forName(arg + "." + s).getConstructor().newInstance());
                }
            }
        }
    }

    public Animal getAnimal (Preferences.AnimalEnum animalEnum) {

        for (Animal prototype : prototypes) {
            if(prototype.getName().equalsIgnoreCase(animalEnum.toString())){
                return prototype.clone();
            }
        }

        //TODO убрать возможность возврата null
        return null;
    }


}
