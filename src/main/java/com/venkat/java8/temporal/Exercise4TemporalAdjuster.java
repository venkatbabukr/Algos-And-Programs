package com.venkat.java8.temporal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.StringJoiner;

import com.venkat.java.exercises.common.SampleExerciseBase;

public class Exercise4TemporalAdjuster extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "TemporalAdjusters";
    
    protected Exercise4TemporalAdjuster() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        
        LocalDate today = LocalDate.now();

        String outputLines = new StringJoiner(System.lineSeparator())
                                 .add(String.format("    1. TemporalAdjusters.firstDayOfMonth().adjustInto(today): %s", TemporalAdjusters.firstDayOfMonth().adjustInto(today)))
                                 .add(String.format("    2. today.with(TemporalAdjusters.lastDayOfMonth()): %s", today.with(TemporalAdjusters.lastDayOfMonth())))
                                 .add(String.format("    3. today.with(TemporalAdjusters.firstDayOfNextMonth()): %s", today.with(TemporalAdjusters.firstDayOfNextMonth())))
                                 .add(String.format("    4. today.with(TemporalAdjusters.firstDayOfYear()): %s", today.with(TemporalAdjusters.firstDayOfYear())))
                                 .add(String.format("    5. today.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)): %s", today.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY))))
                                 .add(String.format("    6. today.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)): %s", today.with(TemporalAdjusters.next(DayOfWeek.SATURDAY))))
                                 .add(String.format("    7. now.with(anonymous adjuster adding 3 half days): %s", LocalDateTime.now().with(temporal -> temporal.plus(3L, ChronoUnit.HALF_DAYS))))
                                 .toString();
        
        // 1. TemporalAdjusters
        printfln("1. TemporalAdjusters:%s%s",
                 System.lineSeparator(),
                 outputLines);
    }
    
    public static void main(String[] args) {
        (new Exercise4TemporalAdjuster()).executeExercise();
    }

}
