package com.venkat.java8.streams;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.venkat.common.util.SampleExerciseBase;

public class Exercise5StreamGeneration extends SampleExerciseBase {
    
    public static final String EXERCISE_TITLE = "Stream Generation";

    public Exercise5StreamGeneration() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1. Stream.of()
        System.out.format("1. Stream.of(): %s\n",
                          Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                                .collect(Collectors.toList()));

        // 2. 5 even numbers using Stream.iterate() and limit()
        System.out.format("2. 5 even numbers using Stream.iterate() and limit(): %s\n",
                          Stream.iterate(0, n -> n + 2)
                                .limit(5)
                                .collect(Collectors.toList()));

        // 3. 5 powers of using Stream.iterate() and limit()
        System.out.format("3. 5 powers of using Stream.iterate() and limit(): %s\n",
                          Stream.iterate(1, n -> n * 2)
                                .limit(5)
                                .collect(Collectors.toList()));

        // 4. 5 random UUIDs using generate()
        System.out.format("4. 5 random UUIDs using generate(): %s\n",
                          Stream.generate(UUID::randomUUID)
                                .limit(5)
                                .collect(Collectors.toList()));

        // 5. Integer stream between (3, 13) using range()
        System.out.format("5. Integer stream between (3, 13) using range(): %s\n",
                          IntStream.range(3, 13)
                                .boxed()
                                .collect(Collectors.toList()));

        // 6. Iterate on array using IntStream.range()
        System.out.format("6. Iterate on array using IntStream.range(): %s\n",
                          IntStream.range(0, ExercisesData.ALL_INTEGERS_ARRAY.length)
                        		.boxed()
                                .map(i -> String.format("Arr[%d]=%d", i, ExercisesData.ALL_INTEGERS_ARRAY[i]))
                                .collect(Collectors.joining(", ")));
    }

    public static void main(String[] args) {
        (new Exercise5StreamGeneration()).executeExercise();
    }

}
