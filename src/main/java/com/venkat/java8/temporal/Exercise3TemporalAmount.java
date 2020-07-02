package com.venkat.java8.temporal;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

import com.venkat.java.exercises.common.SampleExerciseBase;

public class Exercise3TemporalAmount extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "TemporalAmount";
    
    protected Exercise3TemporalAmount() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        
        TemporalAmount exampleDuration = Duration.between(LocalDateTime.now().minusHours(3).minusMinutes(20),
                                                     LocalDateTime.now());

        // 1. get all units within the amount
        printfln("1. get all units in the TemporalAmount(Duration) %s: %s", exampleDuration, exampleDuration.getUnits());
        
        // 2. getAmount using TimeUnit
        printfln("2. get from TemporalAmount(Duration) %s using TimeUnit (Seconds): %s Secs", exampleDuration, exampleDuration.get(ChronoUnit.SECONDS));

        // 3. addTo
        printfln("3. TemporalAmount(Duration) %s addTo(LocalDateTime.now()): %s", exampleDuration, exampleDuration.addTo(LocalDateTime.now()));
        
        // 4. subtractFrom
        printfln("4. TemporalAmount(Duration) %s subtractFrom(LocalDateTime.now()): %s", exampleDuration, exampleDuration.subtractFrom(LocalDateTime.now()));
    }
    
    public static void main(String[] args) {
        (new Exercise3TemporalAmount()).executeExercise();
    }

}
