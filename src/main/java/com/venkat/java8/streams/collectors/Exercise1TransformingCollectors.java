package com.venkat.java8.streams.collectors;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;

import com.venkat.java.exercises.util.ExercisesData;
import com.venkat.java.exercises.util.SampleExerciseBase;
import com.venkat.java.exercises.util.ExercisesData.Meal;

public class Exercise1TransformingCollectors extends SampleExerciseBase {
    
    public static final String EXERCISE_TITLE = "Transforming Collectors";

    public Exercise1TransformingCollectors() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1. List using toList()
        printfln("1. List using toList(): %s",
                 Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                       .collect(toList()));

        // 2. Set using toSet()
        printfln("2. Set using toSet(): %s",
                 Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                       .collect(toSet()));

        // 3. Sorted Set using toCollection()
        printfln("3. Sorted Set using toCollection(): %s",
                 Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                       .collect(toCollection(TreeSet::new)));

        // 4. String using joining()
        printfln("4. String using joining(): %s",
                 Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                       .map(n -> String.valueOf(n))
                       .collect(joining(", ", "Begin String->>\"", "\"<<-End String")));

        // 5. Transform to negative numbers using mapping()
        printfln("5. Transform to negative numbers using mapping(): %s",
                 Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                       .collect(mapping(n -> -1 * n, toList())));

        // 6. Map<number, it's double> using toMap()
        printfln("6. Map<number, it's double> using toMap(): %s",
                 Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                       .collect(
                           toMap(Function.identity(), n -> n * 1.0, (n, m) -> n, TreeMap::new)));

        // 7. Meals List . Map by ID using toMap()
        printfln("7. Meals List . Map by ID using toMap(): %s",
                 ExercisesData.ALL_MEALS
                              .stream()
                              .collect(toMap(Meal::getId, Function.identity())));

        // 8. Meals List . Map by name using toMap()
        printfln("8. Meals List => Map by name using toMap(): %s",
                 ExercisesData.ALL_MEALS
                              .stream()
                              .collect(toMap(Meal::getName, Function.identity(), (u, v) -> u, TreeMap::new)));

    }

    public static void main(String[] args) {
        (new Exercise1TransformingCollectors()).executeExercise();
    }

}
