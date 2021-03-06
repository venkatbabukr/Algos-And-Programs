package com.venkat.java8.functional;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.TreeSet;

import com.venkat.java.exercises.common.ExercisesData;
import com.venkat.java.exercises.common.SampleExerciseBase;
import com.venkat.java.exercises.common.ExercisesData.Meal;
import com.venkat.java.exercises.common.ExercisesData.MealTaste;
import com.venkat.java.exercises.common.ExercisesData.MealType;

public class Exercise1FunctionReferenceTypes extends SampleExerciseBase {
    
    public static final class VenkatMealFilter {

        private MealType acceptType;

        private MealTaste acceptTaste;
        
        public VenkatMealFilter(MealType type, MealTaste taste) {
            this.acceptType = type;
            this.acceptTaste = taste;
        }

        public boolean ok(Meal m) {
            return acceptType.equals(m.getType())
                       || acceptTaste.equals(m.getTaste());
        }
        
    }

    public static final String EXERCISE_TITLE = "Function Reference Types";

    protected Exercise1FunctionReferenceTypes() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {

        // 1. Fliter Meals ok for Venkat using venkatFilter::ok - Object method reference
        VenkatMealFilter venkatFilter = new VenkatMealFilter(MealType.ASIANVEG, MealTaste.SPICY);
        printfln("1. Fliter Meals ok for Venkat using venkatFilter::ok - Object method reference: %s",
                          ExercisesData.ALL_MEALS
                                       .stream()
                                       .filter(venkatFilter::ok)
                                       .collect(toList()));

        // 2. Print using this::printf - Object method reference
        printf("2. Print using this::printf - Object method reference: ");
        Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
              .map(num -> String.format("%d, ", num))
              .forEach(this::printf);
        println();

        // 3. Filter using Meal::isVegMeal - Type method reference
        printfln("3. Filter using Meal::isVegMeal - Type method reference: %s",
                          ExercisesData.ALL_MEALS
                                       .stream()
                                       .filter(Meal::isVegMeal)
                                       .collect(toList()));

        // 4. Map to Hex using Integer::toHexString - Static method reference
        printfln("4. Map to Hex using Integer::toHexString - Static method reference: %s",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .map(Integer::toHexString)
                                .collect(toList()));

        // 5. Add numbers to TreeSet using toCollection(TreeSet::new) - Constructor reference
        printfln("5. Add numbers to TreeSet using toCollection(TreeSet::new) - Constructor reference: %s",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .collect(toCollection(TreeSet::new)));

    }
    
    public static void main(String[] args) {
        (new Exercise1FunctionReferenceTypes()).executeExercise();
    }

}
