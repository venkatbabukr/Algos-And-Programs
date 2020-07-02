package com.venkat.java8.streams.collectors;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Comparator;

import com.venkat.java.exercises.common.ExercisesData;
import com.venkat.java.exercises.common.SampleExerciseBase;

public class Exercise2AggregatingCollectors extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Aggregating Collectors";

    public Exercise2AggregatingCollectors() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1. Count using counting()
        printfln("1. Count using counting(): %s", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                         .collect(counting()));
        // 2. Sum using summingInt()
        printfln("2. Sum using summingInt(): %s", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                         .collect(summingInt(num -> num)));

        // 3. Average using averagingInt()
        printfln("3. Average using averagingInt(): %s", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                               .collect(averagingInt(num -> num)));

        // 4. Product using reducing()
        printfln("4. Product using reducing(): %s", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                           .collect(reducing((prod, n) -> prod * n)));

        // 5. Summary using summarizingInt()
        printfln("5. Summary using summarizingInt(): %s", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                                 .collect(summarizingInt(num -> num)));

        // 6. Maximum using maxBy()
        printfln("6. Maximum using maxBy(): %s", Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                                        .collect(maxBy(Comparator.naturalOrder())));

    }

    public static void main(String[] args) {
        (new Exercise2AggregatingCollectors()).executeExercise();
    }

}
