package com.venkat.java8.streams.collectors;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.TreeSet;

import com.venkat.java.exercises.util.ExercisesData;
import com.venkat.java.exercises.util.SampleExerciseBase;

public class Exercise4PartitioningCollectors extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Partitioning Collectors";

    public Exercise4PartitioningCollectors() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1. Odd/Even partition
        printfln("1. Odd/Even partition: %s", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                     .collect(partitioningBy(i -> i % 2 == 0)));

        // 2. Odd/Even partition set using downstream toSet()
        printfln("2. Odd/Even partition set using downstream toSet(): %s", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                                                  .collect(partitioningBy(i -> i % 2 == 0, toSet())));

        // 3. Odd/Even partition sorted set using downstream toCollection()
        printfln("3. Odd/Even partition sorted set using downstream toCollection(): %s", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                                                                .collect(partitioningBy(i -> i % 2 == 0, toCollection(TreeSet::new))));

    }

    public static void main(String[] args) {
        (new Exercise4PartitioningCollectors()).executeExercise();
    }

}
