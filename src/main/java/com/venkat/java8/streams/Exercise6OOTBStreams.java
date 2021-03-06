package com.venkat.java8.streams;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import com.venkat.java.exercises.common.SampleExerciseBase;

public class Exercise6OOTBStreams extends SampleExerciseBase {
    
    public static final String EXERCISE_TITLE = "OOTB Simple Streams";

    public Exercise6OOTBStreams() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1. IntStream.summaryStatistics()
        printfln("1. IntStream.summaryStatistics(): %s",
                 IntStream.range(1, 10)
                          .summaryStatistics());
        // 2. LongStream.summaryStatistics()
        printfln("2. LongStream.summaryStatistics(): %s",
                          LongStream.range(1, 10)
                                   .summaryStatistics());

        // 3. DoubleStream.summaryStatistics()
        printfln("3. DoubleStream.summaryStatistics(): %s",
                          DoubleStream.of(1.0, 2, 3, 4.0, 5, 6, 7, 8, 9)
                                   .summaryStatistics());
    
    }

    public static void main(String[] args) {
        (new Exercise6OOTBStreams()).executeExercise();
    }

}
