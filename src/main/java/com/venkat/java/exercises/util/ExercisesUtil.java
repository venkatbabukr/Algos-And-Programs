package com.venkat.java.exercises.util;

import java.util.Objects;
import java.util.StringJoiner;

public final class ExercisesUtil {

    private ExercisesUtil() { }

    public static String getTitleLine(String title) {
        return new String(new char[title.length()]).replace('\0', '-');
    }

    public static String getFormattedExerciseTitle(String title) {
        Objects.requireNonNull(title);
        return new StringJoiner(System.lineSeparator())
                    .add(title)
                    .add(getTitleLine(title))
                    .toString();
    }

    public static String getFormattedExerciseCollectionTitle(String title) {
        Objects.requireNonNull(title);
        String titleLine = getTitleLine(title);
        return new StringJoiner(System.lineSeparator())
                   .add(String.format(" -%s- ", titleLine))
                   .add(String.format("| %s |", title))
                   .add(String.format(" -%s- ", titleLine))
                   .toString();
    }

}
