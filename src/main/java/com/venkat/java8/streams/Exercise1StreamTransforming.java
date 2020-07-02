package com.venkat.java8.streams;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.stream.IntStream;

import com.venkat.java.exercises.common.ExercisesData;
import com.venkat.java.exercises.common.SampleExerciseBase;

public class Exercise1StreamTransforming extends SampleExerciseBase {
    
    public static final String EXERCISE_TITLE = "Stream Transforming";

    public Exercise1StreamTransforming() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1. Array using toArray()
        int[] streamToArray = IntStream.range(1, 10).toArray();
        printfln("1. Array using toArray(): %s, %s",
                          streamToArray, Arrays.toString(streamToArray));

        // 2. Transform to double using map()
        printfln("2. Transform to double using map(): %s",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .map(n -> n * 1.0)
                                .collect(toList()));

        // 3. Sorted list using sequential() & sorted()
        printfln("3. Sorted list using sequential() & sorted(): %s",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .sequential()
                                .sorted()
                                .collect(toList()));

    }

    public static void main(String[] args) {
        (new Exercise1StreamTransforming()).executeExercise();
    }

}
