package com.venkat.java8.util;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

import com.venkat.java.exercises.common.SampleExerciseBase;

public class Exercise1Optional extends SampleExerciseBase {
    
    public static final String EXERCISE_TITLE = "Java8 Optional (java.util.Optional)";

    public Exercise1Optional() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        String testString = "Test str";
        //1. Create using Optional.of
        printfln("1. Create using Optional.of <%s>: %s", testString, Optional.of(testString));

        //2. Create using Optional.ofNullable
        printfln("2. Create using Optional.ofNullable: %s", Optional.ofNullable(null));

        //3. Optional.isPresent()
        printfln("3. Optional.isPresent() of <%s>: %s", testString, Optional.of(testString).isPresent());

        //4. Optional.get()
        printfln("4. Optional.get() of <%s>: %s", testString, Optional.of(testString).get());

        //5. Optional.orElse
        printfln("5. Optional.orElse: %s", Optional.ofNullable(null).orElse("Other Non null str!"));

        //6. Optional.ifPresent()
        printf("6. Optional.ifPresent() of <%s>: ", testString);
        Optional.of(testString).ifPresent(str -> printfln("Consuming... %s", str));

        //7. Optional.map() to all upper case
        printfln("7. Optional.map() to all upper case of <%s>: %s", testString, Optional.of(testString).map(s -> s.toUpperCase()));

        //8. OptionalInt
        printfln("8. OptionalInt of <%s>: %s", 1, OptionalInt.of(1));

        //9. OptionalLong
        printfln("9. OptionalLong of <%s>: %s", 1, OptionalLong.of(1));

        //10. OptionalDouble
        printfln("10. OptionalDouble of <%s>: %s", 1, OptionalDouble.of(1));

    }

    public static void main(String[] args) {
        (new Exercise1Optional()).executeExercise();
    }

}
