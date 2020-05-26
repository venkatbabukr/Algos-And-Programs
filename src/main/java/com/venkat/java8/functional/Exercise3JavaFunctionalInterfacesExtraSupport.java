package com.venkat.java8.functional;

import static java.util.stream.Collectors.*;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.venkat.java.exercises.util.ExercisesData;
import com.venkat.java.exercises.util.SampleExerciseBase;

public class Exercise3JavaFunctionalInterfacesExtraSupport extends SampleExerciseBase {
    
    public static final String EXERCISE_TITLE = "Functional Interfaces (java.util.function) additional support";

    protected Exercise3JavaFunctionalInterfacesExtraSupport() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1. BinaryOperator.maxBy in Stream.reduce() to find max in array
        System.out.format("1. BinaryOperator.maxBy in Stream.reduce() to find max in array: %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .reduce(BinaryOperator.maxBy(Comparator.naturalOrder())));

        // 2. Predicate.and in Stream.filter() to find even numbers less than 20
        System.out.format("2. Predicate.and in Stream.filter() to find even numbers less than 20: %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .filter(((Predicate<Integer>)num -> num < 20).and(num -> num % 2 == 0))
                                .collect(toList()));

        // 3. Predicate.negate in Stream.filter() to find numbers greater than 20
        System.out.format("3. Predicate.negate in Stream.filter() to find numbers greater than 20: %s\n",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .filter(((Predicate<Integer>)num -> num < 20).negate())
                                .collect(toList()));

        // 4. BiPredicate too has .and & .negate...
        System.out.format("4. BiPredicate too has .and & .negate...\n");

        // 5. Consumer.andThen - Print number & it's negative - Inside Stream.forEach()
        System.out.print("5. Consumer.andThen - Print number & it's negative - Inside Stream.forEach(): ");
        Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
              .forEach(((Consumer<Integer>) num -> System.out.format("[%d:", num))
                       .andThen(num -> System.out.format("%d], ", num * -1)));
        System.out.println();

        // 6. Function.andThen - Rounding a double number 1.234 andThen formatting
        Double num = 1.234;
        System.out.format("6. Function.andThen - Rounding a double number 1.234 andThen formatting %s: %s\n",
                          num, ((Function<Double, Long>)dnum -> Math.round(dnum))
                                   .andThen((Function<Long, String>) roundedNum -> (new DecimalFormat("$###,###")).format(roundedNum))
                                   .apply(num));

        // 7. Function.compose - Rounding a double number 1.234 andThen formatting - but using compose
        System.out.format("7. Function.compose - Rounding a double number 1.234 andThen formatting - but using compose %s: %s\n",
                          num, ((Function<Long, String>) roundedNum -> (new DecimalFormat("$###,###")).format(roundedNum))
                                   .compose((Function<Double, Long>) dnum -> Math.round(dnum))
                                   .apply(num));
    }

    public static void main(String[] args) {
        (new Exercise3JavaFunctionalInterfacesExtraSupport()).executeExercise();
    }

}
