package com.venkat.java8.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

import com.venkat.common.util.SampleExerciseBase;

public class Exercise2StreamAggregating extends SampleExerciseBase {
    
    public static final String EXERCISE_TITLE = "Stream Aggregating";

    public Exercise2StreamAggregating() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1=> Count using count()
        System.out.format("1=> Count using count(): %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .count());

        // 2=> Sum using reduce()
        System.out.format("2=> Sum using reduce(): %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .reduce((sum, n) -> sum + n));

        // 3=> Matrix Sum using flatMap() & reduce()
        System.out.format("3=> Matrix Sum using flatMap() & reduce(): %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_MATRIX)
                                .flatMap(row -> Stream.of(row))
                                .reduce((sum, n) -> sum + n));

        // 4=> Max using max()
        System.out.format("4=> Max using max(): %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .max(Comparator.naturalOrder()));
    }

    public static void main(String[] args) {
        (new Exercise2StreamAggregating()).run();
    }

}
