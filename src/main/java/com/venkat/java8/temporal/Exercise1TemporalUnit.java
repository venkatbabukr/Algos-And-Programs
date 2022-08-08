package com.venkat.java8.temporal;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import com.venkat.java.exercises.common.SampleExerciseBase;
import com.venkat.utils.Constants.StringConstants;

public class Exercise1TemporalUnit extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "TemporalUnits - ChronoUnit";
    
    protected Exercise1TemporalUnit() {
        super(EXERCISE_TITLE);
    }

    private String getUnitDetails(TemporalUnit unit) {
        return String.format("Duration: %s, Estimated: %s, Date based: %s, Time based: %s",
                             unit.getDuration(),
                             unit.isDurationEstimated(),
                             unit.isDateBased(),
                             unit.isTimeBased());
    }
    
    @Override
    public void exerciseOutput() {

        AtomicInteger subIdx = new AtomicInteger(1);
        // 1. ChronoUnits with details
        printfln("1. ChronoUnits with details:%s%s",
                 System.lineSeparator(),
                 Arrays.stream(ChronoUnit.values())
                       .sorted(comparing(TemporalUnit::getDuration))
                       .sequential()
                       .map(unit -> String.format("    %d. %s: {%s}", subIdx.getAndIncrement(), unit, getUnitDetails(unit)))
                       .collect(joining(System.lineSeparator(), StringConstants.EMPTY_STRING, System.lineSeparator())));

        subIdx.set(1);
        // 2. IsoUnits with details
        printfln("2. IsoUnits with details:%s%s",
                 System.lineSeparator(),
                 Stream.of(IsoFields.WEEK_BASED_YEARS, IsoFields.QUARTER_YEARS)
                       .sorted(comparing(TemporalUnit::getDuration))
                       .sequential()
                       .map(unit -> String.format("    %d. %s: %s", subIdx.getAndIncrement(), unit, getUnitDetails(unit)))
                       .collect(joining(System.lineSeparator(), StringConstants.EMPTY_STRING, System.lineSeparator())));

        // 3. TemporalUnit (ChronoUnit).addTo
        printfln("3. TemporalUnit(ChronoUnit).addTo(LocalDate.now(), 2): %s",
                 ChronoUnit.DAYS.addTo(LocalDate.now(), 2));

        // 4. TemporalUnit (ChronoUnit).between
        printfln("4. TemporalUnit(ChronoUnit).between(LocalDate.now(), LocalDate.now().minusDays(2)): %s",
                 ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().minusDays(2)));

    }
    
    public static void main(String[] args) {
        (new Exercise1TemporalUnit()).executeExercise();
    }

}
