package com.workday.advent.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class Day05Test {

    Day05 day05;

    @BeforeEach
    void setUp() {
        day05 = new Day05();
    }

    @Test
    void easyTest() {
        String partial = "dabAcCaCBAcCcaDA";

        Scanner scanner = new Scanner(partial);
        scanner.useDelimiter("");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }

        String first = partial.substring(partial.length()-1, partial.length());
        System.out.println(first);
    }

    @Test
    void parser() {
        String test = "dabAcCaCBAcCcaDA";

        String reduced = day05.reducePolymer(test);
        System.out.println(String.format("From %s ===> %s", test, reduced));
    }
}
