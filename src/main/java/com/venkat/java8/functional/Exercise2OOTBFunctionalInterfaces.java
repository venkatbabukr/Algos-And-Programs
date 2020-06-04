package com.venkat.java8.functional;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Stream;

import com.venkat.java.exercises.util.ExercisesData;
import com.venkat.java.exercises.util.SampleExerciseBase;

public class Exercise2OOTBFunctionalInterfaces extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "OOTB Functional Interfaces (java.util.function)";
    
    protected Exercise2OOTBFunctionalInterfaces() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1. UnaryOperator in Stream.iterate() - Generate stream of odd numbers
        printfln("1. UnaryOperator in Stream.iterate() - Generate stream of odd numbers: %s",
                          Stream.iterate(1, i -> i + 2)
                                .limit(10)
                                .collect(toList()));

        // 2. BinaryOperator in all reduce functions - Sum of array
        printfln("2. BinaryOperator in all reduce functions - Sum of array: %s",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .reduce((sum, num) -> sum + num));

        // 3. Predicate in Stream.filter - Filter even numbers in array
        printfln("3. Predicate in Stream.filter - Filter even numbers in array: %s",
                          Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
                                .filter(num -> num % 2 == 0)
                                .collect(toList()));
        // 4. BiPredicate - Random example - isSubStr.test(str1, str2)
        BiPredicate<String, String> isSubStr = (str1, str2) -> str1 != null && str2 != null && str1.contains(str2);
        printfln("4. BiPredicate - Random example - isSubStr.test(str1, str2): %s, %s",
                          isSubStr.test("str1", "str2"), isSubStr.test("str1", "tr"));

        // 5. Consumer - Inside Stream.forEach()
        System.out.print("5. Consumer - Inside Stream.forEach(): ");
        Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY)
              .forEach(num -> printf("[%d], ", num));
        println();

        // 6. BiConsumer - Set::add...
        Set<String> mySet = new TreeSet<>();
        BiConsumer<Set<String>, String> addStringToSet = Set<String>::add;
        addStringToSet.accept(mySet, "str1");
        addStringToSet.accept(mySet, "str2");
        addStringToSet.accept(mySet, "str1");
        printfln("6. BiConsumer - Set::add...: %s", mySet);

        // 7. Supplier in Stream.generate - Generate random UUIDs
        printfln("7. Supplier in Stream.generate - Generate random UUIDs: %s",
                Stream.generate(UUID::randomUUID)
                      .limit(10)
                      .collect(toList()));

        // 8. Function - Random Example - Rounding a double number 1.234
        Function<Double, Long> rounding = dnum -> Math.round(dnum);
        Double num = 1.234;
        printfln("8. Function - Random Example - Rounding a double number %s: %s",
                          num, rounding.apply(num));

        // 9. BiFunction - Random Example - 2.0 ^ 3.0
        BiFunction<Double, Double, Double> pow = (x, y) -> Math.pow(x, y);
        double x = 2.0;
        double y = 3.0;
        printfln("9. BiFunction - Random Example - %s ^ %s: %s",
                          x, y, pow.apply(x, y));

    }

    public static void main(String[] args) {
        (new Exercise2OOTBFunctionalInterfaces()).executeExercise();
    }

}
