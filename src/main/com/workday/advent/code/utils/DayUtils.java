package com.workday.advent.code.utils;

import java.io.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    /**
     * Get a diff between two dates
     * @param date1 the oldest date
     * @param date2 the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    public static int getMinuteOfDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).getMinute();
    }
}
