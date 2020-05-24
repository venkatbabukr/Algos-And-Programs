package com.venkat.java8.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.venkat.common.util.SampleExerciseBase;

public class Exercise4StreamIterationAndPredicates extends SampleExerciseBase {
    
    public static final String EXERCISE_TITLE = "Stream Iteration & Predicates";

    public Exercise4StreamIterationAndPredicates() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1=> Iterate using forEach
        System.out.print("1=> Iterate using forEach: ");
        Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
              .forEach(num -> System.out.format("[%d]", num));
        System.out.println();

        // 2=> First number using findFirst()
        System.out.format("2=> First number using findFirst(): %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .findFirst());

        // 3=> Any number using findAny()
        System.out.format("3=> Any number using findAny(): %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .findAny());

        // 4=> Check if all are positive, negative using allMatch()
        System.out.format("4=> Check if all are positive, negative using allMatch(): %s, %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .allMatch(n -> n > 0),
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .allMatch(n -> n < 0));

        // 5=> Check if any even number, negative using anyMatch()
        System.out.format("5=> Check if any even number, negative using anyMatch(): %s, %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .anyMatch(n -> n % 2 == 0),
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .anyMatch(n -> n < 0));

        // 6=> Check if no negatives using noneMatch()
        System.out.format("6=> Check if no negatives using noneMatch(): %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .noneMatch(n -> n < 0));

    }

    public static void main(String[] args) {
        (new Exercise4StreamIterationAndPredicates()).run();
    }

}
