package com.venkat.common.util;

public interface ISampleExercise extends Runnable {

    String exerciseTitle();

    void exerciseOutput();

    default void run() {
        String title = exerciseTitle();
        String titleUnderline = new String(new char[title.length()]).replace('\0', '-');
        System.out.format("%s\n%s\n", title, titleUnderline);
        exerciseOutput();
    }

}
