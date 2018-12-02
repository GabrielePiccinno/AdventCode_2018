package com.workday.advent.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day02Test {
    Day02 day02;

    @BeforeEach
    void setUp() {
        day02 = new Day02();
    }

    @Test
    void count() {
        day02.countRepetitions("puppaaamozzo");
        day02.printStatus();
        day02.printChecksum();
        Assertions.assertTrue(day02.twice == 2);
        Assertions.assertTrue(day02.thrice == 2);
    }

    @Test
    void difference() {
        String f1 = "ziaboiaa";
        String f2 = "ziatroia";
        day02.difference(f1, f2);

        f2 = "ziaboiab";
        day02.difference(f1, f2);
    }
}