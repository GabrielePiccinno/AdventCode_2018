package com.workday.advent.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

class Day04Test {
    Day04 day04;

    @BeforeEach
    void setUp() {
        day04 = new Day04();
    }


    @Test
    void parser() throws ParseException {
        String c1 = "[1518-10-03 00:47] falls asleep";
        Day04.Record record = day04.parseInput(c1);
        System.out.println(record);

        c1 = "[1518-06-22 00:48] wakes up";
        record = day04.parseInput(c1);
        System.out.println(record);

        c1 = "[1518-07-26 23:50] Guard #487 begins shift";
        record = day04.parseInput(c1);
        System.out.println(record);

    }
}