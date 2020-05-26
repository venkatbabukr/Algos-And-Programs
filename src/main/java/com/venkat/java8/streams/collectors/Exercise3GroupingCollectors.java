package com.venkat.java8.streams.collectors;

import static java.util.stream.Collectors.*;

import java.util.TreeMap;

import com.venkat.common.util.SampleExerciseBase;
import com.venkat.java8.streams.ExercisesData;
import com.venkat.java8.streams.ExercisesData.Meal;

public class Exercise3GroupingCollectors extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Grouping Collectors";

    public Exercise3GroupingCollectors() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1. Meals list => Group by time
        System.out.format("1. Meals list => Group by time:\n%s\n", ExercisesData.ALL_MEALS
                                                                              .stream()
                                                                              .collect(groupingBy(Meal::getTime)));

        // 2. Meals list => Nested grouping by <type, time>
        System.out.format("\n2. Meals list => Nested grouping by <type, time>:\n%s\n", ExercisesData.ALL_MEALS
                                                                                                  .stream()
                                                                                                  .collect(groupingBy(Meal::getType,
                                                                                                             groupingBy(Meal::getTime, TreeMap::new, toList()))));
    }

    public static void main(String[] args) {
        (new Exercise3GroupingCollectors()).executeExercise();
    }

}
