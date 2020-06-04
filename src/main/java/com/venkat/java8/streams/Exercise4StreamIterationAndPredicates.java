package com.venkat.java8.streams;

import java.util.Arrays;

import com.venkat.java.exercises.util.ExercisesData;
import com.venkat.java.exercises.util.SampleExerciseBase;

public class Exercise4StreamIterationAndPredicates extends SampleExerciseBase {
    
    public static final String EXERCISE_TITLE = "Stream Iteration & Predicates";

    public Exercise4StreamIterationAndPredicates() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1=> Iterate using forEach
        printf("1=> Iterate using forEach: ");
        Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
              .forEach(num -> printf("[%d] ", num));
        println();

        // 2=> First number using findFirst()
        printfln("2=> First number using findFirst(): %s",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .findFirst());

        // 3=> Any number using findAny()
        printfln("3=> Any number using findAny(): %s",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .findAny());

        // 4=> Check if all are positive, negative using allMatch()
        printfln("4=> Check if all are positive, negative using allMatch(): %s, %s",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .allMatch(n -> n > 0),
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .allMatch(n -> n < 0));

        // 5=> Check if any even number, negative using anyMatch()
        printfln("5=> Check if any even number, negative using anyMatch(): %s, %s",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .anyMatch(n -> n % 2 == 0),
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .anyMatch(n -> n < 0));

        // 6=> Check if no negatives using noneMatch()
        printfln("6=> Check if no negatives using noneMatch(): %s",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .noneMatch(n -> n < 0));

    }

    public static void main(String[] args) {
        (new Exercise4StreamIterationAndPredicates()).executeExercise();
    }

}
