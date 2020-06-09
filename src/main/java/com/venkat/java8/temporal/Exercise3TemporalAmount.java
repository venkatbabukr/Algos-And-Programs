package com.venkat.java8.temporal;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

import com.venkat.java.exercises.util.SampleExerciseBase;

public class Exercise3TemporalAmount extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "TemporalAmount";
    
    protected Exercise3TemporalAmount() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        
        TemporalAmount sample = Duration.between(LocalDateTime.now()
                                                     .minusHours(3).minusMinutes(20),
                                                 LocalDateTime.now());

        // 1. get all units within the amount
        printfln("1. get all units within the amount: %s", sample.getUnits());
        
        // 2. getAmount using TimeUnit
        printfln("2. getAmount using TimeUnit (Seconds): %s Secs", sample.get(ChronoUnit.SECONDS));

        // 3. addTo
        printfln("3. addTo: %s", sample.addTo(LocalDateTime.now()));
        
        // 4. subtractFrom
        printfln("4. subtractFrom: %s", sample.subtractFrom(LocalDateTime.now()));
    }
    
    public static void main(String[] args) {
        (new Exercise3TemporalAmount()).executeExercise();
    }

}
