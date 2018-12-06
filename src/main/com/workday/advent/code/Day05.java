package com.workday.advent.code;

import com.workday.advent.code.utils.DayUtils;

import java.util.List;
import java.util.Scanner;

public class Day05 extends DayUtils {
    final String EMPTY = "";

    public String reducePolymer(String polymer) {
        Scanner scanner = new Scanner(polymer);
        scanner.useDelimiter("");
        String reduced = EMPTY;
        String last = EMPTY;
//        while (scanner.hasNext()) {
//            last = atomicReduce(scanner, last);
//            System.out.println(last);
//            reduced+=last;
//        }
        for (int i = 0; i < polymer.toCharArray().length; i++) {
            String first = ""+polymer.charAt(i);
            
        }
        if (!polymer.equals(reduced)) {
            System.out.println("Now: " + reduced);
            return reducePolymer(reduced);
        } else {
            return reduced;
        }
    }

    public String atomicReduce(String first, String second) {
        if (first.equals(first.toLowerCase())) {
            if (second.equals(first.toUpperCase())) {
                return EMPTY;
            } else {
                return first+second;
            }
        } else {
            if (second.equals(first.toLowerCase())) {
                return EMPTY;
            } else {
                return first+second;
            }
        }
    }

    public String atomicReduce(Scanner reader, String partial) {
        String first;
        if (!partial.isEmpty()) {
            first = partial;
        } else {
            first=reader.next();
        }
        System.out.println(first);
        String second = EMPTY;
        if (reader.hasNext()) {
            second = reader.next();
        } else {
            return first;
        }
        System.out.println(second);
        if (first.equals(first.toLowerCase())) {
            if (second.equals(first.toUpperCase())) {
                return EMPTY;
            } else {
                return first+second;
            }
        } else {
            if (second.equals(first.toLowerCase())) {
                return EMPTY;
            } else {
                return first+second;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Day05 day05 = new Day05();
        List<String> input = day05.readFileAsList("d05_input.txt");
    }
}
