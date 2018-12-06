package com.workday.advent.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Arrays;

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


    @Test
    void trytest() throws ParseException {
        Integer integer = day04.mostAsleep.putIfAbsent(1, 5);
        System.out.println(integer);
        integer = day04.mostAsleep.putIfAbsent(1, 34);
        System.out.println(integer);

        integer = day04.mostAsleep.merge(1, 66, (k, v) -> v+1);
        System.out.println(integer);

        integer = day04.mostAsleep.compute(1, (k, v) -> v == null ? 1 : v+1);
        System.out.println(integer);
        integer = day04.mostAsleep.compute(2, (k, v) -> v == null ? 1 : v+1);
        System.out.println(integer);

        int[] intero = new int[4];
        System.out.println(Arrays.toString(intero));
    }
}