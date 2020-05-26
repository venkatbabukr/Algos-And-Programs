package com.venkat.java8.streams;

import java.util.Arrays;
import java.util.List;

public final class ExercisesData {

    public static enum MealTime {
        BREAKFAST, LUNCH, SNACK, DINNER;
    }

    public static enum MealTaste {
        SWEET, SPICY, SOUR, BLAND;
    }

    public static enum MealType {
        ASIANVEG, NONVEG, CONTINENTAL, JAINVEG;
    }

    public static class Meal {
        private int id;
        private String name;
        private MealTime mealTime;
        private MealTaste taste;
        private MealType type;

        public Meal(int id, String name, MealTime time, MealTaste tst, MealType type) {
            this.id = id;
            this.name = name;
            this.mealTime = time;
            this.taste = tst;
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public MealTime getTime() {
            return mealTime;
        }

        public MealTaste getTaste() {
            return taste;
        }

        public MealType getType() {
            return type;
        }

        public boolean isVegMeal() {
        	return MealType.ASIANVEG.equals(type) || MealType.JAINVEG.equals(type);
        }

        public String toString() {
            return String.format("%s[%s/%s]", name, type, mealTime);
        }

    }

    public static final Integer[] ALL_INTEGERS_ARRAY = new Integer[] { 12, 39, 2, 1, 8, 22, 84, 94, 1, 4, 2, 10 };

    public static final Integer[][] ALL_INTEGERS_MATRIX = new Integer[][] { {12, 39, 2},
                                                                            {1, 8, 22,},
                                                                            {84, 94, 1},
                                                                            {4, 2, 10}};

    public static final List<Meal> ALL_MEALS = Arrays.asList(
            new Meal(1, "Toast/Sandwitch", MealTime.BREAKFAST, MealTaste.BLAND, MealType.CONTINENTAL),
            new Meal(2, "Tuna Fish", MealTime.DINNER, MealTaste.SPICY, MealType.NONVEG),
            new Meal(3, "Doritos", MealTime.SNACK, MealTaste.SPICY, MealType.CONTINENTAL),
            new Meal(4, "Steamed Rice", MealTime.LUNCH, MealTaste.BLAND, MealType.ASIANVEG),
            new Meal(5, "Idly Sambar", MealTime.BREAKFAST, MealTaste.BLAND, MealType.ASIANVEG));

}