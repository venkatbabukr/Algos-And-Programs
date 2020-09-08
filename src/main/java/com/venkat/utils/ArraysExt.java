package com.venkat.utils;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public final class ArraysExt {

    private ArraysExt() {
    }

    public static boolean isEmpty(byte[] arr) {
        return arr == null || arr.length == 0;
    }

    public static boolean isEmpty(char[] arr) {
        return arr == null || arr.length == 0;
    }

    public static boolean isEmpty(int[] arr) {
        return arr == null || arr.length == 0;
    }

    public static boolean isEmpty(long[] arr) {
        return arr == null || arr.length == 0;
    }

    public static boolean isEmpty(double[] arr) {
        return arr == null || arr.length == 0;
    }

    public static boolean isEmpty(boolean[] arr) {
        return arr == null || arr.length == 0;
    }

    public static <T> boolean isEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    public static boolean isEmpty(byte[][] arr) {
        return arr == null || arr.length == 0 || Arrays.stream(arr).allMatch(row -> row == null || row.length == 0);
    }

    public static boolean isEmpty(char[][] arr) {
        return arr == null || arr.length == 0 || Arrays.stream(arr).allMatch(row -> row == null || row.length == 0);
    }

    public static boolean isEmpty(int[][] arr) {
        return arr == null || arr.length == 0 || Arrays.stream(arr).allMatch(row -> row == null || row.length == 0);
    }

    public static boolean isEmpty(long[][] arr) {
        return arr == null || arr.length == 0 || Arrays.stream(arr).allMatch(row -> row == null || row.length == 0);
    }

    public static boolean isEmpty(double[][] arr) {
        return arr == null || arr.length == 0 || Arrays.stream(arr).allMatch(row -> row == null || row.length == 0);
    }

    public static boolean isEmpty(boolean[][] arr) {
        return arr == null || arr.length == 0 || Arrays.stream(arr).allMatch(row -> row == null || row.length == 0);
    }

    public static <T> boolean isEmpty(T[][] arr) {
        return arr == null || arr.length == 0 || Arrays.stream(arr).allMatch(row -> row == null || row.length == 0);
    }
    
    public static String to2DString(int[][] arr) {
        return Optional.ofNullable(arr)
                   .map(arr2D -> Arrays.stream(arr2D)
                                     .sequential()
                                     .map(row -> Arrays.toString(row))
                                     .collect(Collectors.joining(System.lineSeparator())))
                   .orElse(Constants.NULL_STR);
    }

}
