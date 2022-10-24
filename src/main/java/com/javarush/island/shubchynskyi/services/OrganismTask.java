package com.javarush.island.shubchynskyi.services;



import com.javarush.island.shubchynskyi.entity.animals.Organism;

//тут будут задания для очереди
public class OrganismTask {

    private final Organism organism;

    public OrganismTask(Organism organism) {
        this.organism = organism;
    }

    public void startTask(){
        if (organism.isAlive()) {
            organism.startLife();
        }
//        else {
//            System.err.println("Мертво!");
//        }
    }

}
