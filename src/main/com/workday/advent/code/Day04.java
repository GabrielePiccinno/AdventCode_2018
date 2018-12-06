package com.workday.advent.code;

import com.workday.advent.code.utils.DayUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Day04 extends DayUtils {
    Map<Integer, Integer> mostAsleep = new HashMap<>();
    Map<Integer, int[]> mostMinuteAsleep = new HashMap<>();
    Map<Integer, int[]> mostMinuteAsleep2 = new HashMap<>();
    int lastGuardId;
    Date wentAsleep;
    int wentAsleepMinute;


    public class Record implements Comparable {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm");
        Date time;
        int minute;
        int guardId;
        boolean isAsleep;
        boolean isAwake;

        public Record(String input) throws ParseException {
            parseInput(input);
        }

        public void parseInput(String input) throws ParseException {
            time = format.parse(input.substring(input.indexOf("[")+1, input.indexOf("]")));
            minute = Integer.parseInt(input.substring(15, 17));
            if (input.contains("Guard #")) {
                String id = input.substring(input.indexOf("#")+1, input.indexOf(" begins"));
                if (id != null) {
                    guardId = Integer.valueOf(id);
                }
            }
            isAsleep = input.contains("asleep");
            isAwake = input.contains("wakes");
        }

        @Override
        public String toString() {
            return String.format("Date: %s; IDGuard: %s; isAsleep: %s; isAwake: %s", time, guardId, isAsleep, isAwake);
        }


        @Override
        public int compareTo(Object o) {
            Record r = (Record) o;
            return  time.compareTo(r.time);
        }
    }

    public Record parseInput(String input) throws ParseException {
        return new Record(input);
    }

    public void countAsleep(String input) throws ParseException {
        Record record = parseInput(input);
        System.out.println(record);
        if (record.guardId > 0) {
            lastGuardId = record.guardId;
        }
        if (record.isAsleep) {
            wentAsleep = record.time;
            wentAsleepMinute = record.minute;
        }
        if (record.isAwake) {
            int minutesDiff = Math.toIntExact(DayUtils.getDateDiff(wentAsleep, record.time, TimeUnit.MINUTES));
            mostAsleep.compute(lastGuardId, (id, countAsleep) -> countAsleep == null ? minutesDiff : countAsleep+minutesDiff);
            mostMinuteAsleep.putIfAbsent(lastGuardId, new int[60]);
            for (int i = DayUtils.getMinuteOfDate(wentAsleep); i < DayUtils.getMinuteOfDate(record.time); i++) {
                mostMinuteAsleep.get(lastGuardId)[i] += 1;
            }
            mostMinuteAsleep2.putIfAbsent(lastGuardId, new int[60]);
            for (int i = wentAsleepMinute; i < record.minute; i++) {
                mostMinuteAsleep2.get(lastGuardId)[i] += 1;
            }
        }
        System.out.println("Map: " + mostAsleep);
    }

    public int calculateMaxMinute(int[] minutes) {
        System.out.println(Arrays.toString(minutes));
        int maxMinute = 0;
        int indexMinute = 0;
        for (int i = 0; i < minutes.length; i++) {
            if (maxMinute < minutes[i]) {
                maxMinute = minutes[i];
                indexMinute = i;
            }
        }
        return indexMinute;
    }

    public int calculateMaxMinute2(int[] minutes) {
        System.out.println(Arrays.toString(minutes));
        int maxMinute = 0;
        int indexMinute = 0;
        for (int i = 0; i < minutes.length; i++) {
            if (maxMinute < minutes[i]) {
                maxMinute = minutes[i];
                indexMinute = i;
            }
        }
        return maxMinute;
    }


    public static void alternative(String[] args) throws Exception {
        Day04 day04 = new Day04();
        Map<Integer, int[]> data = new HashMap<Integer, int[]>();
        List<String> input = day04.readFileAsList("d04_input.txt");
        Collections.sort(input);
        int current = 0;
        int start = 0;
        for (String l : input) {
            int min = Integer.parseInt(l.substring(15, 17));
            boolean shift = l.contains("shift");
            boolean wake = l.contains("wake");
            boolean sleep = l.contains("sleep");

            int id;
            if (shift) {
                id = Integer.parseInt(l.split(" ")[3].substring(1));
                current = id;
                if (!data.containsKey(id)) {
                    data.put(current, new int[60]);
                    Arrays.fill(data.get(current), 0);
                }
            }
            if (sleep) {
                start = min;
            }
            if (wake) {
                for (int i = start; i < min; i++) {
                    data.get(current)[i] += 1;
                }
            }
        }

        List<Integer> keys = new ArrayList(data.keySet());
        int best = keys.get(0);
        int bestsum = 0;
        for (int id : keys) {
            int sum = Arrays.stream(data.get(id)).filter(x -> x >= 1).sum();
            if (sum > bestsum) {
                bestsum = sum;
                best = id;
            }
        }
        int bestMinute = 0;
        int bestTime = 0;
        System.out.println(Arrays.toString(data.get(best)));
        for (int i = 0; i < 60; i++) {
            if (data.get(best)[i] > bestMinute) {
                bestMinute = data.get(best)[i];
                bestTime = i;
            }
        }
        System.out.println("Guard id: " + best);
        System.out.println("Minute: " + bestTime);
        System.out.println("Solution to part 1: " + best * bestTime);

        best = keys.get(0);
        bestMinute = 0;
        for (int id : keys) {
            int minute = Arrays.stream(data.get(id)).max().orElse(-1);
            if (minute > bestMinute) {
                bestMinute = minute;
                best = id;
            }
        }

        bestTime = 0;
        for(int i = 0; i < 60; i++) {
            if(data.get(best)[i] == bestMinute) {
                bestTime = i;
            }
        }
        System.out.println("Guard id: " + best);
        System.out.println("Minute: " + bestTime);
        System.out.println("Solution to part 2: " + best * bestTime);
    }


    public static void main(String[] args) throws Exception {
        Day04 day04 = new Day04();
        List<String> input = day04.readFileAsList("d04_input.txt");
        Collections.sort(input);
        for (String text : input) {
            System.out.println(text);
            day04.countAsleep(text);
        }
        Integer max = Collections.max(day04.mostAsleep.values());
        int maxGuard = 0;
        for (int key: day04.mostAsleep.keySet()) {
            if (day04.mostAsleep.get(key)==max) {
                maxGuard = key;
                break;
            }
        }
        int[] minutes = day04.mostMinuteAsleep.get(maxGuard);
        int indexMinute = day04.calculateMaxMinute(minutes);
        System.out.println(String.format("GuardID: %s, MaxMinutes: %s, MostMinute: %s ===> %s", maxGuard, max, indexMinute, maxGuard*indexMinute));
        minutes = day04.mostMinuteAsleep2.get(maxGuard);
        indexMinute = day04.calculateMaxMinute(minutes);
        System.out.println(String.format("GuardID: %s, MaxMinutes: %s, MostMinute: %s ===> %s", maxGuard, max, indexMinute, maxGuard*indexMinute));

        Map<Integer, Integer> mostSleptMinute = new HashMap<>();
        for (int id : day04.mostMinuteAsleep2.keySet()) {
            mostSleptMinute.putIfAbsent(id, day04.calculateMaxMinute2(day04.mostMinuteAsleep2.get(id)));
        }
        System.out.println(mostSleptMinute);
        max = Collections.max(mostSleptMinute.values());
        for (int key: mostSleptMinute.keySet()) {
            if (mostSleptMinute.get(key)==max) {
                maxGuard = key;
                break;
            }
        }
        System.out.println(String.format("MaxGuard: %s; MaxMinute: %s ==> %s", maxGuard, max, max*maxGuard));
        minutes = day04.mostMinuteAsleep2.get(maxGuard);
        indexMinute = day04.calculateMaxMinute(minutes);
        System.out.println(String.format("GuardID: %s, MaxMinutes: %s, MostMinute: %s ===> %s", maxGuard, max, indexMinute, maxGuard*indexMinute));

        alternative(args);
    }

}
