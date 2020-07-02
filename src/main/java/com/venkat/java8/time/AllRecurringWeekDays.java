package com.venkat.java8.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.venkat.java.exercises.common.SampleExerciseBase;

public class AllRecurringWeekDays extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "All recurring weekdays";
    
    protected AllRecurringWeekDays() {
        super(EXERCISE_TITLE);
    }

    public List<Temporal> getAllRecurringDates(Temporal fromDate, int totalReqd, DayOfWeek... reqdWeekDays) {
        Objects.requireNonNull(reqdWeekDays);
        Arrays.sort(reqdWeekDays);
        Temporal start = fromDate.with(TemporalAdjusters.nextOrSame(reqdWeekDays[0]));
        return Stream.iterate(start, dt -> dt.plus(Period.ofDays(7)))
                     .limit(totalReqd)
                     .map(firstReqdDayOfWeek -> Arrays.stream(reqdWeekDays).map(wkDay -> wkDay.adjustInto(firstReqdDayOfWeek)))
                     .flatMap(Function.identity())
                     .collect(Collectors.toList());
    }
    
    @Override
    public void exerciseOutput() {
        int totalReqd = 5;
        LocalDate today = LocalDate.now();
        printfln("All %s Tuesdays and Thursdays from today %s: %s", totalReqd,
                     today, getAllRecurringDates(today, totalReqd, DayOfWeek.TUESDAY, DayOfWeek.THURSDAY));
    }
    
    public static void main(String[] args) {
        (new AllRecurringWeekDays()).executeExercise();
    }

}
