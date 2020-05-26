package com.venkat.java8.streams;

import static java.util.stream.Collectors.*;

import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.venkat.java.exercises.util.ExercisesData;
import com.venkat.java.exercises.util.SampleExerciseBase;

public class Exercise5StreamGenerationAndConcat extends SampleExerciseBase {
    
    public static final String EXERCISE_TITLE = "Stream Generation & simple Concat";

    public Exercise5StreamGenerationAndConcat() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1. Stream.of()
        System.out.format("1. Stream.of(): %s\n",
                          Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                                .collect(toList()));

        // 2. 5 even numbers using Stream.iterate() and limit()
        System.out.format("2. 5 even numbers using Stream.iterate() and limit(): %s\n",
                          Stream.iterate(0, n -> n + 2)
                                .limit(5)
                                .collect(toList()));

        // 3. 5 powers of using Stream.iterate() and limit()
        System.out.format("3. 5 powers of using Stream.iterate() and limit(): %s\n",
                          Stream.iterate(1, n -> n * 2)
                                .limit(5)
                                .collect(toList()));

        // 4. 5 random UUIDs using generate()
        System.out.format("4. 5 random UUIDs using generate(): %s\n",
                          Stream.generate(UUID::randomUUID)
                                .limit(5)
                                .collect(toList()));

        // 5. Integer stream between (3, 13) using range()
        System.out.format("5. Integer stream between (3, 13) using range(): %s\n",
                          IntStream.range(3, 13)
                                .boxed()
                                .collect(toList()));

        // 6. Iterate on array using IntStream.range()
        System.out.format("6. Iterate on array using IntStream.range(): %s\n",
                          IntStream.range(0, ExercisesData.ALL_INTEGERS_ARRAY.length)
                        		.boxed()
                                .map(i -> String.format("Arr[%d]=%d", i, ExercisesData.ALL_INTEGERS_ARRAY[i]))
                                .collect(joining(", ")));

        // 7. Concatenate two streams using Stream.concat()
        System.out.format("7. Concatenate two streams using Stream.concat(): %s\n",
                          Stream.concat(Stream.of(1, 2, 3), Stream.of(4, 5, 6))
                                .collect(toList()));

        // 8. Concatenate multiple streams using Stream.of() and then flatMap()
        System.out.format("8. Concatenate multiple streams using Stream.of() and then flatMap(): %s\n",
                          Stream.of(IntStream.range(1, 4).boxed(),
                                    IntStream.range(4, 7).boxed(),
                                    IntStream.range(7, 10).boxed())
                                .flatMap(i -> i)
                                .collect(toList()));

    }

    public static void main(String[] args) {
        (new Exercise5StreamGenerationAndConcat()).executeExercise();
    }

}
