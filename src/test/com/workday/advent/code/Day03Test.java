package com.workday.advent.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day03Test {
    Day03 day03;

    @BeforeEach
    void setUp() {
        day03 = new Day03();
    }


    @Test
    void parser() {
        String c1 = "#1 @ 1,3: 4x4";
        Day03.Claim claim = day03.parseInput(c1);
        System.out.println(claim);

        c1 = "#3 @ 5,5: 2x2";
        claim = day03.parseInput(c1);
        System.out.println(claim);

        c1 = "#406 @ 619,507: 10x27";
        claim = day03.parseInput(c1);
        System.out.println(claim);

    }
}