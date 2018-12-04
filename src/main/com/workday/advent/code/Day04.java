package com.workday.advent.code;

import com.workday.advent.code.utils.DayUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Day04 extends DayUtils {

    public class Record {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm");
        Date time;
        int guardId;
        boolean isAsleep;
        boolean isAwake;

        public Record(String input) throws ParseException {
            parseInput(input);
        }

        public void parseInput(String input) throws ParseException {
            time = format.parse(input.substring(input.indexOf("[")+1, input.indexOf("]")));
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
    }

    public Record parseInput(String input) throws ParseException {
        return new Record(input);
    }



}
