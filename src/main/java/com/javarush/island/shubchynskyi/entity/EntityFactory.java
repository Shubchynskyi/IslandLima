package com.javarush.island.shubchynskyi.entity;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import com.javarush.island.shubchynskyi.settings.EntitySettings;
import com.javarush.island.shubchynskyi.utils.Generator;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.javarush.island.shubchynskyi.settings.EntitySettings.animalPrototypes;


public class EntityFactory {


    // вынести в отдельный метод класса gameField
    public Cell getFilledCell(int x, int y) {
        Cell resultCell = new Cell(x, y);

        for (Animal prototype : animalPrototypes) {
            resultCell.animalsInCell.put(prototype.getAvatar(),new CopyOnWriteArraySet<>());
            int randomInt = Generator.getRandom(0, prototype.getMaxPerCell());
            for (int i = 0; i <= randomInt; i++) {
                resultCell.animalsInCell.get(prototype.getAvatar()).add(prototype.clone(resultCell));
            }
        }

        for (Plant prototype : EntitySettings.plantPrototypes) {
            resultCell.plantsInCell.put(prototype.getAvatar(), new CopyOnWriteArraySet<>());
            int randomInt = Generator.getRandom(0, prototype.getMaxPerCell());
            for (int i = 0; i <= randomInt; i++) {
                resultCell.plantsInCell.get(prototype.getAvatar()).add(prototype.clone(resultCell));
            }
        }

        return resultCell;
    }

    // TODO убрать и сделать прототипы отдельным классом (это и будет фабрика)
    //
//     рождаем новых животных / рождаем новые растения
    public void getAnimal (Animal animal) {
        Set<Animal> result = new HashSet<>();
//        Animal result = null;

        for (Animal prototype : animalPrototypes) {
//            if(prototype.getType() == type){
//                result = prototype.clone();
//            }
        }

        //TODO убрать возможность возврата null
//        return (Set<Animal>) result;
    }




}
