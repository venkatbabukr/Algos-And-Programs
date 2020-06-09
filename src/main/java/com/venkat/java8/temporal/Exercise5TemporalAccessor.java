package com.venkat.java8.temporal;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalQueries;
import java.util.StringJoiner;

import com.venkat.java.exercises.util.SampleExerciseBase;

public class Exercise5TemporalAccessor extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "TemporalAccessor - Read only";
    
    protected Exercise5TemporalAccessor() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {

        // 1. get()
        String outputLines = new StringJoiner(System.lineSeparator())
                                 .add(String.format("\ti.\tInstant.now().get(NANO_OF_SECOND): %s", Instant.now().get(ChronoField.NANO_OF_SECOND)))
                                 .add(String.format("\tii.\tLocalDate.now().get(DAY_OF_YEAR): %s", LocalDate.now().get(ChronoField.DAY_OF_YEAR)))
                                 .add(String.format("\tiii.\tLocalTime.now().get(HOUR_OF_DAY): %s", LocalTime.now().get(ChronoField.HOUR_OF_DAY)))
                                 .add(String.format("\tiv.\tLocalDateTime.now().get(AMPM_OF_DAY): %s", LocalDateTime.now().get(ChronoField.AMPM_OF_DAY)))
                                 .toString();
        
        printfln("1. get(<TemporalField>):%s%s",
                 System.lineSeparator(),
                 outputLines);

        // 2. isSupported()
        outputLines = new StringJoiner(System.lineSeparator())
                          .add(String.format("\ti.\tInstant.isSupported(NANO_OF_SECOND): %s", Instant.now().isSupported(ChronoField.DAY_OF_YEAR)))
                          .add(String.format("\tii.\tLocalDate.isSupported(HOUR_OF_AMPM): %s", LocalDate.now().isSupported(ChronoField.HOUR_OF_AMPM)))
                          .add(String.format("\tiii.\tLocalTime.isSupported(YEAR): %s", LocalTime.now().isSupported(ChronoField.YEAR)))
                          .add(String.format("\tiv.\tLocalDateTime.isSupported(NANO_OF_DAY): %s", LocalDateTime.now().isSupported(ChronoField.NANO_OF_DAY)))
                          .toString();
        printfln("2. isSupported(<TemporalField>):%s%s",
                 System.lineSeparator(),
                 outputLines);

        // 3. query() get TemporalUnit using precision()
        outputLines = new StringJoiner(System.lineSeparator())
                          .add(String.format("\ti.\tInstant.query(precision()): %s", Instant.now().query(TemporalQueries.precision())))
                          .add(String.format("\tii.\tLocalDate.query(precision()): %s", LocalDate.now().query(TemporalQueries.precision())))
                          .add(String.format("\tiii.\tLocalTime.query(precision()): %s", LocalTime.now().query(TemporalQueries.precision())))
                          .add(String.format("\tiv.\tLocalDateTime.query(precision()): %s", LocalDateTime.now().query(TemporalQueries.precision())))
                          .toString();
        printfln("3. query(<R>) query <TemporalUnit> using precision():%s%s",
                 System.lineSeparator(),
                 outputLines);

    }
    
    public static void main(String[] args) {
        (new Exercise5TemporalAccessor()).executeExercise();
    }

}
