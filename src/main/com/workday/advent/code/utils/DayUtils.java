package com.workday.advent.code.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DayUtils {
    protected BufferedReader readFile(String filename) throws FileNotFoundException {
        File file = new File("./src/resource/"+filename);
        System.out.println(file.exists());
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        return br;
    }

    protected List<String> readFileAsList(String filename) throws IOException {
        BufferedReader br = readFile(filename);
        List<String> input = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            input.add(line);
        }
        return input;
    }
}
