package com.venkat.java8.temporal;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.StringJoiner;

import com.venkat.java.exercises.util.SampleExerciseBase;

public class Exercise6Temporal extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Temporal - Perform airthmetic";
    
    protected Exercise6Temporal() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {

        // 1. plus()
        String outputLines = new StringJoiner(System.lineSeparator())
                                 .add(String.format("\ti.\tInstant.now().plus(Duration.ofHours(2L))): %s", Instant.now().plus(Duration.ofHours(2L))))
                                 .add(String.format("\tii.\tLocalDate.now().plusDays(3): %s", LocalDate.now().plusDays(3)))
                                 .add(String.format("\tiii.\tLocalTime.now().plus(2, ChronoUnit.HALF_DAYS): %s", LocalTime.now().plus(2, ChronoUnit.HALF_DAYS)))
                                 .add(String.format("\tiv.\tLocalDateTime.now().plusHours(2L).plusMinutes(2L): %s", LocalDateTime.now().plusHours(2L).plusMinutes(2L)))
                                 .toString();
        
        printfln("1. plus():%s%s",
                 System.lineSeparator(),
                 outputLines);

        // 2. minus()
        outputLines = new StringJoiner(System.lineSeparator())
                          .add(String.format("\ti.\tInstant.now().minus(Duration.ofHours(2L))): %s", Instant.now().minus(Duration.ofHours(2L))))
                          .add(String.format("\tii.\tLocalDate.now().minusDays(3): %s", LocalDate.now().minusDays(3)))
                          .add(String.format("\tiii.\tLocalTime.now().minus(2, ChronoUnit.HALF_DAYS): %s", LocalTime.now().minus(2, ChronoUnit.HALF_DAYS)))
                          .add(String.format("\tiv.\tLocalDateTime.now().minusHours(2L).minusMinutes(2L): %s", LocalDateTime.now().minusHours(2L).minusMinutes(2L)))
                          .toString();
        
        printfln("2. minus():%s%s",
                 System.lineSeparator(),
                 outputLines);

        // 3. with()
        println("3. with() same as exercise shown in TemporalAdjuster");

    }
    
    public static void main(String[] args) {
        (new Exercise6Temporal()).executeExercise();
    }

}
