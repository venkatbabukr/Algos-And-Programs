package com.venkat.java8.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.venkat.java.exercises.common.SampleExerciseBase;

public class AllMondaysInMonth extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Print all mondays in this month!";
    
    public AllMondaysInMonth() {
        super(EXERCISE_TITLE);
    }

    private List<Temporal> getAllMondaysOfMonth(YearMonth yrmth) {
        Temporal start = LocalDate.now()
                                  .with(yrmth)
                                  .with(TemporalAdjusters.firstDayOfMonth())
                                  .with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

        // Limit to 6 as we can't get more than 5 Monday's in a month...
        return Stream.iterate(start, dt -> dt.plus(Period.ofDays(7)))
                     .limit(6)
                     .filter(t -> t.get(ChronoField.MONTH_OF_YEAR) == yrmth.getMonth().getValue())
                     .collect(Collectors.toList());
    }
    
    @Override
    public void exerciseOutput() {
        YearMonth thisMonth = YearMonth.now();
        printfln("All Mondays in this month %s: %s", thisMonth, getAllMondaysOfMonth(thisMonth));
    }

    
    public static void main(String[] args) {
        (new AllMondaysInMonth()).executeExercise();
    }
}
