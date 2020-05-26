package com.venkat.java8.functional;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.TreeSet;

import com.venkat.java.exercises.util.ExercisesData;
import com.venkat.java.exercises.util.SampleExerciseBase;
import com.venkat.java.exercises.util.ExercisesData.Meal;
import com.venkat.java.exercises.util.ExercisesData.MealTaste;
import com.venkat.java.exercises.util.ExercisesData.MealType;

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
        System.out.format("1. Fliter Meals ok for Venkat using venkatFilter::ok - Object method reference: %s\n",
                          ExercisesData.ALL_MEALS
                                       .stream()
                                       .filter(venkatFilter::ok)
                                       .collect(toList()));

        // 2. Print using System.out::print - Object method reference
        System.out.print("2. Print using System.out::print - Object method reference: ");
        Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
              .map(num -> String.format("%d, ", num))
              .forEach(System.out::print);
        System.out.println();

        // 3. Filter using Meal::isVegMeal - Type method reference
        System.out.format("3. Filter using Meal::isVegMeal - Type method reference: %s\n",
                          ExercisesData.ALL_MEALS
                                       .stream()
                                       .filter(Meal::isVegMeal)
                                       .collect(toList()));

        // 4. Map to Hex using Integer::toHexString - Static method reference
        System.out.format("4. Map to Hex using Integer::toHexString - Static method reference: %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .map(Integer::toHexString)
                                .collect(toList()));

        // 5. Add numbers to TreeSet using toCollection(TreeSet::new) - Constructor reference
        System.out.format("5. Add numbers to TreeSet using toCollection(TreeSet::new) - Constructor reference: %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .collect(toCollection(TreeSet::new)));

    }
    
    public static void main(String[] args) {
        (new Exercise1FunctionReferenceTypes()).executeExercise();
    }

}
