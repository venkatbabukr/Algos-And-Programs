package com.venkat.java8.streams.collectors;

import static java.util.stream.Collectors.*;

import java.util.Arrays;

import com.venkat.java.exercises.common.ExercisesData;
import com.venkat.java.exercises.common.SampleExerciseBase;

public class Exercise5AndThenCollectors extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "AndThen Collectors";

    public Exercise5AndThenCollectors() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {

        // 1. Add Title to the partitioned map...
        final String partitionMapTitle = "Odd/Even Partition map with {true: <even numbers>, false: <odd numbers>}:";
        String outputMessage = Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                     .collect(collectingAndThen(partitioningBy(i -> i % 2 == 0),
                                                  pMap -> String.join(" ", partitionMapTitle, pMap.toString())));
        printfln("1. %s", outputMessage);
    }

    public static void main(String[] args) {
        (new Exercise5AndThenCollectors()).executeExercise();
    }

}
