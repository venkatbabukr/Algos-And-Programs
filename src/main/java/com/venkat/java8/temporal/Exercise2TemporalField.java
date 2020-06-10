package com.venkat.java8.temporal;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.IsoFields;
import java.time.temporal.JulianFields;
import java.time.temporal.TemporalField;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import com.venkat.algos.utils.Constants;
import com.venkat.java.exercises.util.SampleExerciseBase;

public class Exercise2TemporalField extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "TemporalField - ChronoField";
    
    protected Exercise2TemporalField() {
        super(EXERCISE_TITLE);
    }

    private String getFieldDetails(TemporalField field) {
        return String.format("Base unit: %s, Range unit: %s, Value range: %s",
                             field.getBaseUnit(),
                             field.getRangeUnit(),
                             field.range());
    }
    
    @Override
    public void exerciseOutput() {

        AtomicInteger subIdx = new AtomicInteger(1);
        // 1. ChronoFields with details
        printfln("1. ChronoFields with details:%s%s",
                 System.lineSeparator(),
                 Arrays.stream(ChronoField.values())
                       .sorted(comparingLong(field -> field.getBaseUnit().getDuration().getSeconds()))
                       .sequential()
                       .map(field -> String.format("    %d. %s: %s", subIdx.getAndIncrement(), field, getFieldDetails(field)))
                       .collect(joining(System.lineSeparator(), Constants.EMPTY_STRING, System.lineSeparator())));

        subIdx.set(1);
        // 2. IsoUnits with details
        printfln("2. IsoFields with details:%s%s",
                 System.lineSeparator(),
                 Stream.of(IsoFields.DAY_OF_QUARTER, IsoFields.WEEK_BASED_YEAR, IsoFields.WEEK_OF_WEEK_BASED_YEAR, IsoFields.QUARTER_OF_YEAR)
                       .sequential()
                       .map(field -> String.format("    %d. %s: %s", subIdx.getAndIncrement(), field, getFieldDetails(field)))
                       .collect(joining(System.lineSeparator(), Constants.EMPTY_STRING, System.lineSeparator())));

        subIdx.set(1);
        // 3. JulianFields with details
        printfln("3. JulianFields with details:%s%s",
                 System.lineSeparator(),
                 Stream.of(JulianFields.JULIAN_DAY, JulianFields.RATA_DIE)
                       .sequential()
                       .map(field -> String.format("    %d. %s: %s", subIdx.getAndIncrement(), field, getFieldDetails(field)))
                       .collect(joining(System.lineSeparator(), Constants.EMPTY_STRING, System.lineSeparator())));

        // 4. TemporalField (ChronoField).getFrom
        printfln("4. TemporalField(ChronoField).getFrom(LocalDateTime.now()): %s",
                 ChronoField.HOUR_OF_AMPM.getFrom(LocalDateTime.now()));
    }
    
    public static void main(String[] args) {
        (new Exercise2TemporalField()).executeExercise();
    }

}
