package com.javarush.island.shubchynskyi.utils;

import com.javarush.island.shubchynskyi.exception.IslandException;
import com.javarush.island.shubchynskyi.settings.EntitySettings;

public class FieldCreator {
    private FieldCreator(){}

    public static Object getField(Object object, String fieldName) {
        try {
            return EntitySettings.class.getField(object.getClass().getSimpleName().toLowerCase() + fieldName)
                    .get(EntitySettings.class);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IslandException("field create error - ", e);
        }
    }

}
