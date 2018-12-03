package com.workday.advent.code;

import com.workday.advent.code.utils.DayUtils;

import java.io.IOException;
import java.util.List;

public class Day03 extends DayUtils {
    int[][] matrix = new int[1000][1000];
    int overlap = 0;

    public class Claim {
        int x;
        int y;
        int width;
        int height;

        @Override
        public String toString() {
            return String.format("X: %s; Y: %s; width: %s; height: %s", x, y, width, height);
        }
    }

    public Claim parseInput(String input) {
        if (input == null)
            return null;
        Claim c = new Claim();
        String[] split = input.replaceAll("\\D+", " ").split(" ");
        c.x = Integer.valueOf(split[2]);
        c.y = Integer.valueOf(split[3]);
        c.width = Integer.valueOf(split[4]);
        c.height = Integer.valueOf(split[5]);
        return c;

    }

    public void evaluateOverlap(Claim claim) {
        System.out.println(claim);
        for (int i = claim.x; i < claim.x+claim.width; i++) {
            for (int j = claim.y; j < claim.y+claim.height; j++) {
                matrix[i][j]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Day03 day03 = new Day03();
        List<String> input = day03.readFileAsList("d03_input.txt");
        for (String text : input) {
            Claim claim = day03.parseInput(text);
            day03.evaluateOverlap(claim);
        }

        for (int i = 0; i < day03.matrix.length; i++) {
            for (int j = 0; j < day03.matrix[i].length; j++) {
                if (day03.matrix[i][j] > 1) {
                    day03.overlap++;
                }
            }
        }

        System.out.println(day03.overlap);
    }
}
