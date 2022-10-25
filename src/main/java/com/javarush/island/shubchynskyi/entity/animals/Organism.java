package com.javarush.island.shubchynskyi.entity.animals;

public interface Organism {

    // TODO вынести больше общих методов
    boolean isAlive();

    String getAvatar();

    void startLife();
}
