package com.venkat.java8.streams.collectors;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Comparator;

import com.venkat.common.util.SampleExerciseBase;
import com.venkat.java8.streams.ExercisesData;

public class Exercise2AggregatingCollectors extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Aggregating Collectors";

    public Exercise2AggregatingCollectors() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1=> Count using counting()
        System.out.format("1=> Count using counting(): %s\n", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                                    .collect(counting()));
        // 2=> Sum using summingInt()
        System.out.format("2=> Sum using summingInt(): %s\n", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                                         .collect(summingInt(num -> num)));

        // 3=> Average using averagingInt()
        System.out.format("3=> Average using averagingInt(): %s\n", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                                          .collect(averagingInt(num -> num)));

        // 4=> Product using reducing()
        System.out.format("4=> Product using reducing(): %s\n", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                                          .collect(reducing((prod, n) -> prod * n)));

        // 5=> Summary using summarizingInt()
        System.out.format("5=> Summary using summarizingInt(): %s\n", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                                            .collect(summarizingInt(num -> num)));

        // 6=> Maximum using maxBy()
        System.out.format("6=> Maximum using maxBy(): %s\n", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                                   .collect(maxBy(Comparator.naturalOrder())));

    }

    public static void main(String[] args) {
        (new Exercise2AggregatingCollectors()).executeExercise();
    }

}
