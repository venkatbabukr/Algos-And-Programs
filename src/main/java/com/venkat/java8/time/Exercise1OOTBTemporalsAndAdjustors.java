package com.venkat.java8.time;

import static java.util.stream.Collectors.toList;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.StringJoiner;
import java.util.stream.Stream;

import com.venkat.java.exercises.util.SampleExerciseBase;

public class Exercise1OOTBTemporalsAndAdjustors extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "OOTB Temporals & Adjustors";
    
    protected Exercise1OOTBTemporalsAndAdjustors() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {

        // 1. OOTB classes
        printfln("1. OOTB classes: %s",
                 Stream.of(DayOfWeek.class, MonthDay.class, Month.class, YearMonth.class, Year.class)
                       .map(clazz -> clazz.getName())
                       .collect(toList()));

        // 2. As TemporalAccessor from now:
        String subLines = new StringJoiner(System.lineSeparator())
                              .add(String.format("    1. DayOfWeek.from(LocalDateTime.now()): %s", DayOfWeek.from(LocalDateTime.now())))
                              .add(String.format("    2. MonthDay.now(): %s", MonthDay.now()))
                              .add(String.format("    3. Month.from(LocalDateTime.now()): %s", Month.from(LocalDateTime.now())))
                              .add(String.format("    4. YearMonth.now(): %s", YearMonth.now()))
                              .add(String.format("    5. Year.now(): %s", Year.now()))
                              .toString();

        printfln("3. As TemporalAccessor from now: %s%s",
                System.lineSeparator(),
                subLines);

        // 3. As Adjustors
        Temporal t1 = LocalDateTime.now();
        subLines = new StringJoiner(System.lineSeparator())
                         .add(String.format("    1. DayOfWeek - Next Tuesday: %s", t1.with(TemporalAdjusters.next(DayOfWeek.TUESDAY))))
                         .add(String.format("    2. MonthDay - Next month 20th date: %s", t1.with(MonthDay.of(t1.get(ChronoField.MONTH_OF_YEAR) + 1, 20))))
                         .add(String.format("    3. Month - This February date: %s", t1.with(Month.FEBRUARY)))
                         .add(String.format("    4. YearMonth - Next year 2nd month: %s", t1.with(YearMonth.of(t1.get(ChronoField.YEAR) + 1, 2))))
                         .add(String.format("    5. Year - Next year same day: %s", t1.with(Year.now().plusYears(1L))))
                         .toString();

        printfln("3. As Adjustors: %s%s",
                 System.lineSeparator(),
                 subLines);
    }
    
    public static void main(String[] args) {
        (new Exercise1OOTBTemporalsAndAdjustors()).executeExercise();
    }

}
