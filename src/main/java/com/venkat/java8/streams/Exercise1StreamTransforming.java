package com.venkat.java8.streams;

import static java.util.stream.Collectors.*;

import java.util.Arrays;

import com.venkat.common.util.SampleExerciseBase;

public class Exercise1StreamTransforming extends SampleExerciseBase {
    
    public static final String EXERCISE_TITLE = "Stream Transforming";

    public Exercise1StreamTransforming() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1=> Transform to double map()
        System.out.format("1=> Transform to double map(): %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .map(n -> n * 1.0)
                                .collect(toList()));
    }

    public static void main(String[] args) {
        (new Exercise1StreamTransforming()).run();
    }

}
