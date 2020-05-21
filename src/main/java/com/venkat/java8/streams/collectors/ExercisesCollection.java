package com.venkat.java8.streams.collectors;

import java.util.Arrays;
import java.util.List;

import com.venkat.common.util.ISampleExercise;

public class ExercisesCollection {

    public static void main(String[] args) {
        List<ISampleExercise> exercisesList = Arrays.asList(new Exercise1TransformingCollectors(),
                new Exercise2AggregatingCollectors(), new Exercise3GroupingCollectors(),
                new Exercise4PartitioningCollectors(), new Exercise5AndThenCollectors());

        for (ISampleExercise exercise : exercisesList) {
            exercise.run();
            System.out.println();
        }
    }

}
