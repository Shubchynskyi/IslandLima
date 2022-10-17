package com.javarush.island.shubchynskyi.entity;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import com.javarush.island.shubchynskyi.preferences.AnimalPref;
import com.javarush.island.shubchynskyi.preferences.PlantPref;
import com.javarush.island.shubchynskyi.utils.Generator;

import java.util.*;


public class EntityFactory {



    public Cell getFilledCell(int x, int y) {
        Cell resultCell = new Cell(x, y);
        for (Animal prototype : AnimalPref.prototypes) {
            int randomInt = Generator.getRandom(0, prototype.getMaxPerCell());
            prototype.setCurrentCell(resultCell);
            for (int i = 0; i <= randomInt; i++) {
                if (resultCell.animalsInCell.containsKey(prototype.getType())) {
                    resultCell.animalsInCell.get(prototype.getType()).add(prototype.clone());
                } else {
                    Set<Animal> newSet = new HashSet<>();
                    newSet.add(prototype.clone());
                    resultCell.animalsInCell.put(prototype.getType(), newSet);
                }
            }
        }

        for (Plant prototype : PlantPref.prototypes) {
            int randomInt = Generator.getRandom(0, prototype.getMaxPerCell());
            for (int i = 0; i <= randomInt; i++) {
                if (resultCell.plantsInCell.containsKey(prototype.getType())) {
                    resultCell.plantsInCell.get(prototype.getType()).add(prototype.clone());
                } else {
                    Set<Plant> newSet = new HashSet<>();
                    newSet.add(prototype.clone());
                    resultCell.plantsInCell.put(prototype.getType(), newSet);
                }
            }
        }

        return resultCell;
    }

//    // TODO remove if not used
    // рождаем новых животных / рождаем новые растения
//    public Animal getAnimal (Preferences.AnimalEnum animalEnum) {
//
//        for (Animal prototype : prototypes) {
//            if(prototype.getName().equalsIgnoreCase(animalEnum.toString())){
//                return prototype.clone();
//            }
//        }
//
//        //TODO убрать возможность возврата null
//        return null;
//    }




}
