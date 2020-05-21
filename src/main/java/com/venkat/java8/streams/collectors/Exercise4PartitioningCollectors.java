package com.venkat.java8.streams.collectors;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.TreeSet;

import com.venkat.common.util.SampleExerciseBase;

public class Exercise4PartitioningCollectors extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Partitioning Collectors";

    public Exercise4PartitioningCollectors() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1=> Odd/Even partition
        System.out.format("1=> Odd/Even partition: %s\n", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                                .collect(partitioningBy(i -> i % 2 == 0)));

        // 2=> Odd/Even partition set using downstream toSet()
        System.out.format("2=> Odd/Even partition set using downstream toSet(): %s\n", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                                                             .collect(partitioningBy(i -> i % 2 == 0, toSet())));

        // 3=> Odd/Even partition sorted set using downstream toCollection()
        System.out.format("3=> Odd/Even partition sorted set using downstream toCollection(): %s\n", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                                                                           .collect(partitioningBy(i -> i % 2 == 0, toCollection(TreeSet::new))));

    }

    public static void main(String[] args) {
        (new Exercise4PartitioningCollectors()).run();
    }

}
