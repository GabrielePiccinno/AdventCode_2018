package com.workday.advent.code;

import com.workday.advent.code.utils.DayUtils;

import java.util.*;

public class Day05 extends DayUtils {
    final String EMPTY = "";
    Map<String, Integer> improved = new HashMap<>();

    public String reducePolymer(String polymer) {
        Scanner scanner = new Scanner(polymer);
        scanner.useDelimiter("");
        String reduced = EMPTY;
        for (int i = 0; i < polymer.toCharArray().length-1; i++) {
            char first = polymer.charAt(i);
            char second = polymer.charAt(i+1);

            String partial = atomicReduce2(first, second);
            reduced += partial;
            if (partial.equals(EMPTY)) {
                i++;
            }

        }
        reduced += polymer.charAt(polymer.length()-1);
        if (!polymer.equals(reduced)) {
//            System.out.println("Now: " + reduced);
            return reducePolymer(reduced);
        } else {
            return reduced;
        }
    }

    public String atomicReduce2(char c1, char c2) {
        if ((c1 ^ c2) == 32) {
            return EMPTY;
        } else {
            return EMPTY+c1;
        }
    }

    public String atomicReduce(char c1, char c2) {
        String first = EMPTY+c1;
        String second = EMPTY+c2;
        if (first.equals(first.toLowerCase())) {
            if (second.equals(first.toUpperCase())) {
                return EMPTY;
            } else {
                return first;
            }
        } else {
            if (second.equals(first.toLowerCase())) {
                return EMPTY;
            } else {
                return first;
            }
        }
    }

    public String reduceImproved(String polymer) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (char c: alphabet) {
            String polymerBetter = polymer.replaceAll(String.valueOf(c), EMPTY).replaceAll(String.valueOf(c).toUpperCase(), EMPTY);
            String reducedPolymer = reducePolymer(polymerBetter);
            improved.putIfAbsent(String.valueOf(c), reducedPolymer.length());
            System.out.println(String.format("Removing: %s => length: %s ===> %s", c, reducedPolymer.length(), reducedPolymer));
        }
        System.out.println(improved);

        Integer min = Collections.min(improved.values());
        int minBest = 20000;
        String minChar = EMPTY;
        for (String key: improved.keySet()) {
            if (improved.get(key)<minBest) {
                minBest = improved.get(key);
                minChar = key;
            }
        }
        System.out.println(String.format("Min Polymer: %s; minSize: %s", minChar, minBest));
        return minChar;

    }

    public static void main(String[] args) throws Exception {
        Day05 day05 = new Day05();
        List<String> input = day05.readFileAsList("d05_input.txt");
        System.out.println(input.size());
        String reduced = day05.reducePolymer(input.get(0));
        System.out.println(reduced);
        System.out.println(reduced.length());

        String improved = day05.reduceImproved(input.get(0));
        System.out.println(String.format("Min Polymer: %s", improved));

    }
}
