package com.venkat.java.exercises.util;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Objects;

public interface ISampleExercise {

    String exerciseTitle();

    void exerciseOutput();

    void setPrintStream(PrintStream pout);
    
    PrintStream getPrintStream();

    default void printf(String formatStr, Object... args) {
        getPrintStream().format(formatStr, args);
    }

    default void println() {
        getPrintStream().println();
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

    default void executeExercise() {
        printfln(ExercisesUtil.getFormattedExerciseTitle(exerciseTitle()));
        exerciseOutput();
    }

}
