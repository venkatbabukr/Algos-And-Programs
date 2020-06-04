package com.venkat.java8.util;

import static java.util.Comparator.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.venkat.java.exercises.util.ExercisesData;
import com.venkat.java.exercises.util.ExercisesData.Meal;
import com.venkat.java.exercises.util.SampleExerciseBase;

public class Exercise4ComparatorEnhancements extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Java8 Comparator Enhancements (java.util.Comparator)";
    
    public Exercise4ComparatorEnhancements() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        
        Integer[] allIntegersCopy = Arrays.copyOf(ExercisesData.ALL_INTEGERS_ARRAY, ExercisesData.ALL_INTEGERS_ARRAY.length);
        List<Meal> allMealsCopy = new ArrayList<>(ExercisesData.ALL_MEALS);

        // 1. Anonymous comparator example (x, y) -> y.compareTo(x)
        Arrays.sort(allIntegersCopy, (x, y) -> y - x);
        printfln("1=> Anonymous comparator example (x, y) -> y - x: %s",
                 Arrays.toString(allIntegersCopy));

        // 2. Comparator.reverseOrder()
        Arrays.sort(allIntegersCopy, reverseOrder());
        printfln("2=> Comparator.reverseOrder(): %s",
                 Arrays.toString(allIntegersCopy));

        // 3. Comparator.reversed()
        Collections.sort(allMealsCopy, comparing(Meal::getTime).reversed());
        printfln("3=> Comparator.reversed(): %s",
                 allMealsCopy);
        
        // 4. Comparator.comparing() sort meals on time
        Collections.sort(allMealsCopy, comparing(Meal::getTime));
        printfln("4=> Comparator.comparing() sort meals on time: %s",
                 allMealsCopy);

        // 5. Comparator.comparing() sort meals on time reverse order
        Collections.sort(allMealsCopy, comparing(Meal::getTime, reverseOrder()));
        printfln("5=>Comparator.comparing() sort meals on time reverse order: %s",
                 allMealsCopy);

        // 6. Comparator.thenComparing sort meals on time and name
        Collections.sort(allMealsCopy, comparing(Meal::getTime).thenComparing(Meal::getName));
        printfln("6=> Comparator.thenComparing sort meals on time and name: %s",
                 allMealsCopy);

        // 7. Comparator.nullsFirst() sort meals on time
        allMealsCopy.addAll(Arrays.asList(null, null, null));
        Collections.sort(allMealsCopy, nullsFirst(comparing(Meal::getTime)));
        printfln("7=> Comparator.nullsFirst() sort meals on time: %s",
                 allMealsCopy);

    }
    
    public static void main(String[] args) {
        (new Exercise4ComparatorEnhancements()).executeExercise();
    }

}
