package com.venkat.java8.streams;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.venkat.java.exercises.common.ExercisesData;
import com.venkat.java.exercises.common.SampleExerciseBase;

public class Exercise3StreamFiltering extends SampleExerciseBase {
    
    public static final String EXERCISE_TITLE = "Stream Filtering";

    public Exercise3StreamFiltering() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1. Numbers divisible by 3
        printfln("1. Numbers divisible by 3: %s",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .filter(n -> n % 3 == 0)
                                .collect(Collectors.toList()));

        // 2. Distinct numbers
        printfln("2. Distinct numbers: %s",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .distinct()
                                .collect(Collectors.toList()));

    }

    public static void main(String[] args) {
        (new Exercise3StreamFiltering()).executeExercise();
    }

}
