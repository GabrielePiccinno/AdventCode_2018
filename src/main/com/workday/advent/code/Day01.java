package com.workday.advent.code;

import com.workday.advent.code.utils.DayUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day01 extends DayUtils {
    int frequency = 0;
    List<Integer> buffer = new ArrayList<>();



    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");

        Day01 day1 = new Day01();
        while (!day1.readFile()) {
            System.out.println("Last is " + day1.frequency);
        }

        // what is the last frequency reached?
        System.out.println("Last is " + day1.frequency);
    }

    public boolean readFile() throws IOException {
        System.out.println("Entering with " + frequency);
        BufferedReader br = readFile("d01_input.txt");
        String line;
        while((line = br.readLine()) != null){
            Integer v = Integer.valueOf(line);
            frequency += v;
//            System.out.println("Current is " + frequency);
//            System.out.println("Buffer is " + buffer.contains(frequency));
            if (buffer.contains(frequency)) {
                // when is the first time the same frequency is reached twice
                System.out.println("Twice is " + frequency);
                return true;
            } else {
                buffer.add(frequency);
            }
        }
        return false;
    }
}
