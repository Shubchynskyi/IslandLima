package com.javarush.island.shubchynskyi.services;


import com.javarush.island.shubchynskyi.entity.animals.Animal;

//тут будут задания для очереди
public class AnimalTask {

    private final Animal animal;

    public AnimalTask(Animal animal) {
        this.animal = animal;
    }

    public void startTask(){
        if (animal.isAlive()) {
//            animal.eat();
//            animal.move();
            animal.spawn();
        }
//        else {
//            System.err.println("Животное мертво!");
//        }
    }

}
