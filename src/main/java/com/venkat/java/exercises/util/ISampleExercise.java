package com.venkat.java.exercises.util;

import java.io.PrintStream;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;

public interface ISampleExercise {

    String exerciseTitle();

    void exerciseOutput();

    void setPrintStream(PrintStream pout);
    
    PrintStream getPrintStream();

    default void print(Object obj) {
        getPrintStream().print(obj);
    }

    default void println(Object obj) {
        getPrintStream().println(obj);
    }

    default void println() {
        getPrintStream().println();
    }

    default void printf(String formatStr, Object... args) {
        getPrintStream().format(formatStr, args);
    }

    default void printfln(String formatStr, Object... args) {
        Objects.requireNonNull(formatStr);
        formatStr = formatStr.concat("%s");
        if (args == null || args.length == 0) {
            args = new Object[] { null };
        } else {
            args = Arrays.copyOf(args, args.length + 1);
        }
        args[args.length - 1] = System.lineSeparator();
        getPrintStream().format(formatStr, args);
    }

    default void printExerciseDuration(Instant start, Instant end) {
        Duration timeTaken = Duration.between(start, end);
        String durationPrintStr = String.format("Total time taken: %s", timeTaken);
        String durationTitleLine = ExercisesUtil.getTitleLine(durationPrintStr);
        printfln("%s", String.join(System.lineSeparator(), durationTitleLine, durationPrintStr, durationTitleLine));
    }

    default void executeExercise(PrintStream pout) {
        setPrintStream(pout);
        printfln(ExercisesUtil.getFormattedExerciseTitle(exerciseTitle()));
        Instant start = Instant.now();
        exerciseOutput();
        Instant end = Instant.now();
        printExerciseDuration(start, end);
    }

    default void executeExercise() {
        executeExercise(System.out);
    }

}
