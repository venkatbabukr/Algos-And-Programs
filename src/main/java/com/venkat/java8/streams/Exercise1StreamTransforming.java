package com.venkat.java8.streams;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.stream.IntStream;

import com.venkat.common.util.SampleExerciseBase;

public class Exercise1StreamTransforming extends SampleExerciseBase {
    
    public static final String EXERCISE_TITLE = "Stream Transforming";

    public Exercise1StreamTransforming() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1=> Array using toArray()
        int[] streamToArray = IntStream.range(1, 10).toArray();
        System.out.format("1=> Array using toArray(): %s, %s\n",
                          streamToArray, Arrays.toString(streamToArray));

        // 2=> Transform to double using map()
        System.out.format("2=> Transform to double using map(): %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .map(n -> n * 1.0)
                                .collect(toList()));

        // 3=> Sorted list using sequential() & sorted()
        System.out.format("3=> Sorted list using sequential() & sorted(): %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .sequential()
                                .sorted()
                                .collect(toList()));

    }

    public static void main(String[] args) {
        (new Exercise1StreamTransforming()).executeExercise();
    }

}
