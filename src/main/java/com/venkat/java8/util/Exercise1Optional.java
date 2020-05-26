package com.venkat.java8.util;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

import com.venkat.java.exercises.util.SampleExerciseBase;

public class Exercise1Optional extends SampleExerciseBase {
    
    public static final String EXERCISE_TITLE = "Java8 Optional (java.util.Optional)";

    public Exercise1Optional() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        String testString = "Test str";
        //1. Create using Optional.of
        System.out.format("1. Create using Optional.of <%s>: %s\n", testString, Optional.of(testString));

        //2. Create using Optional.ofNullable
        System.out.format("2. Create using Optional.ofNullable: %s\n", Optional.ofNullable(null));

        //3. Optional.isPresent()
        System.out.format("3. Optional.isPresent() of <%s>: %s\n", testString, Optional.of(testString).isPresent());

        //4. Optional.get()
        System.out.format("4. Optional.get() of <%s>: %s\n", testString, Optional.of(testString).get());

        //5. Optional.orElse
        System.out.format("5. Optional.orElse: %s\n", Optional.ofNullable(null).orElse("Other Non null str!"));

        //6. Optional.ifPresent()
        System.out.format("6. Optional.ifPresent() of <%s>: ", testString);
        Optional.of(testString).ifPresent(str -> System.out.format("Consuming... %s\n", str));

        //7. Optional.map() to all upper case
        System.out.format("7. Optional.map() to all upper case of <%s>: %s\n", testString, Optional.of(testString).map(s -> s.toUpperCase()));

        //8. OptionalInt
        System.out.format("8. OptionalInt of <%s>: %s\n", 1, OptionalInt.of(1));

        //9. OptionalLong
        System.out.format("9. OptionalLong of <%s>: %s\n", 1, OptionalLong.of(1));

        //10. OptionalDouble
        System.out.format("10. OptionalDouble of <%s>: %s\n", 1, OptionalDouble.of(1));

    }

    public static void main(String[] args) {
        (new Exercise1Optional()).executeExercise();
    }

}
