package com.workday.advent.code;

import com.workday.advent.code.utils.DayUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day02 extends DayUtils {
    int twice = 0;
    int thrice = 0;
    String string1;
    String string2;
    String string3;


    public void countRepetitions(String input) {
        Map<Character, Long> map = new HashMap<>();
        System.out.println(input);
        for (int i = 0; i < input.length(); i++) {
            if (!map.containsKey(input.charAt(i))) {
                int finalI = i;
                long count = input.codePoints().filter(ch -> ch == input.charAt(finalI)).count();
                if (count > 1) {
                    map.put(input.charAt(i), count);
                }
            }
        }
        if (map.values().contains(2l)) twice++;
        if (map.values().contains(3l)) thrice++;

        System.out.println(map);
    }

    public void printStatus() {
        System.out.println(String.format("Twice: %s Thrice: %s", twice, thrice));
    }

    public void printChecksum() {
        System.out.println(String.format("Checksum: %s", twice*thrice));
    }

    public void difference(String f1, String f2) {
        int diff = 0;
        int lastIndex = 0;
        for (int i = 0; i <f1.length() ; i++) {
            if (f1.charAt(i) != f2.charAt(i)) {
                diff++;
                lastIndex=i;
            }
        }
        System.out.println(String.format("Difference: %s (lastIndex: %s)", diff, lastIndex));
        if (diff == 1) {
            string1=f1;
            string2=f2;
            string3=string1.substring(0,lastIndex)+string1.substring(lastIndex+1,string1.length());
            printDifference();
        }
    }

    public void printDifference() {
        System.out.println(String.format("f1: %s f2: %s ==> %s", string1, string2, string3));
    }

    public String getCommonLetters(BufferedReader reader) {
        List<String> lines = reader.lines().collect(Collectors.toList());
        for (int i = 0; i < lines.size(); i++) {
            String firstLine = lines.get(i);
            for (int j = i + 1; j < lines.size(); j++) {
                String secondLine = lines.get(j);
                int distance = 0;
                StringBuilder commonLetters = new StringBuilder();
                for (int k = 0; k < firstLine.length(); k++) {
                    if (firstLine.charAt(k) != secondLine.charAt(k)) {
                        if (++distance > 1) {
                            break;
                        }
                    } else {
                        commonLetters.append(firstLine.charAt(k));
                    }
                }
                if (distance == 1) {
                    return commonLetters.toString();
                }
            }
        }
        return "";
    }


    public int getChecksum(BufferedReader reader) throws IOException {
        String line;
        int doubleDigits = 0;
        int tripleDigits = 0;
        while ((line = reader.readLine()) != null) {
            Map<Character, Integer> frequencies = new HashMap<>();
            for (char c : line.toCharArray()) {
                frequencies.merge(c, 1, (a, b) -> a + b);
            }
            System.out.println(String.format("Map: %s", frequencies));
            Set<Integer> uniqueFrequencies = new HashSet<>(frequencies.values());
            if (uniqueFrequencies.contains(2)) doubleDigits++;
            if (uniqueFrequencies.contains(3)) tripleDigits++;
            System.out.println(String.format("Twice: %s Thrice: %s", doubleDigits, tripleDigits));
        }
        return doubleDigits * tripleDigits;
    }

    public static void main(String[] args) throws IOException {
        Day02 day02 = new Day02();
        List<String> input = day02.readFileAsList("d02_input.txt");
        for (String line: input) {
            day02.countRepetitions(line);
            day02.printStatus();
        }
        day02.printChecksum();
//        BufferedReader br = day02.readFile("d02_input.txt");
//        System.out.println(day02.getChecksum(br));

        for (int i = 0; i < input.size(); i++) {
            for (int j = i; j < input.size(); j++) {
                if (i != j) {
                    day02.difference(input.get(i), input.get(j));
                }
            }
        }
        day02.printDifference();
    }
}

