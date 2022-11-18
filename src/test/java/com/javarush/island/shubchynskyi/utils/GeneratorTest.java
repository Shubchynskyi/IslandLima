package com.javarush.island.shubchynskyi.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import static org.junit.jupiter.api.Assertions.*;

//@Suite
//@SuiteDisplayName("JUnit Platform Suite Demo")
//@SelectPackages("com.javarush.test")
//@IncludeClassNamePatterns(".*Tests")
@DisplayName("имя для класса с тестами")
class GeneratorTest {

    @Test
    void getRandom() {
    }

    @Test
    @DisplayName("Проверка входных данных")
    void getRandomForSpawn() {
    }

    @Test
    void checkChance() {
    }
}