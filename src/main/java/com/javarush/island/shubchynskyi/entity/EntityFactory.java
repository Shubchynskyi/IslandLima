package com.javarush.island.shubchynskyi.entity;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import com.javarush.island.shubchynskyi.settings.EntitySettings;
import com.javarush.island.shubchynskyi.utils.Generator;

import java.util.*;


public class EntityFactory {



    public Cell getFilledCell(int x, int y) {
        Cell resultCell = new Cell(x, y);

        for (Animal prototype : EntitySettings.animalPrototypes) {
            resultCell.animalsInCell.put(prototype.getAvatar(),new HashSet<>());
            int randomInt = Generator.getRandom(0, prototype.getMaxPerCell());
            for (int i = 0; i <= randomInt; i++) {
                resultCell.animalsInCell.get(prototype.getAvatar()).add(prototype.clone(resultCell));
            }
        }

        for (Plant prototype : EntitySettings.plantPrototypes) {
            resultCell.plantsInCell.put(prototype.getAvatar(), new HashSet<>());
            int randomInt = Generator.getRandom(0, prototype.getMaxPerCell());
            for (int i = 0; i <= randomInt; i++) {
                resultCell.plantsInCell.get(prototype.getAvatar()).add(prototype.clone());
            }
        }

        return resultCell;
    }

//    // TODO remove if not used
    // рождаем новых животных / рождаем новые растения
//    public Animal getAnimal (Constants.AnimalEnum animalEnum) {
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
