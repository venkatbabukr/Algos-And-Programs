package com.venkat.java8.streams.collectors;

import static java.util.stream.Collectors.*;

import java.util.TreeMap;

import com.venkat.java.exercises.util.ExercisesData;
import com.venkat.java.exercises.util.SampleExerciseBase;
import com.venkat.java.exercises.util.ExercisesData.Meal;

public class Exercise3GroupingCollectors extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Grouping Collectors";

    public Exercise3GroupingCollectors() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1. Meals list => Group by time
        printfln("1. Meals list => Group by time:%s%s",
                 System.lineSeparator(),
                 ExercisesData.ALL_MEALS
                              .stream()
                              .collect(groupingBy(Meal::getTime)));

        // 2. Meals list => Nested grouping by <type, time>
        println();
        printfln("2. Meals list => Nested grouping by <type, time>:%s%s",
                 System.lineSeparator(),
                 ExercisesData.ALL_MEALS
                              .stream()
                              .collect(groupingBy(Meal::getType,
                                                     groupingBy(Meal::getTime, TreeMap::new, toList()))));
    }

    public static void main(String[] args) {
        (new Exercise3GroupingCollectors()).executeExercise();
    }

}
