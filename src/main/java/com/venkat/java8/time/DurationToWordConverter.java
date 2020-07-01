package com.venkat.java8.time;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Function;

import com.venkat.algos.utils.Constants;
import com.venkat.java.exercises.util.SampleExerciseBase;

public class DurationToWordConverter extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Duration to word converter";

    private List<TemporalUnit> allUnits = null;
    
    private Map<TemporalUnit, Function<Duration, Long>> durationUnitsMap = null;

    public DurationToWordConverter() {
        super(EXERCISE_TITLE);

        ChronoUnit[] allChronoUnits = ChronoUnit.values();
        allUnits = new ArrayList<>(allChronoUnits.length + 1);
        allUnits.addAll(Arrays.asList(allChronoUnits));
        allUnits.add(IsoFields.QUARTER_YEARS);
        Collections.sort(allUnits, Comparator.comparing(TemporalUnit::getDuration).reversed());

        durationUnitsMap = new LinkedHashMap<>();
        durationUnitsMap.put(ChronoUnit.MINUTES, Duration::toMinutes);
        durationUnitsMap.put(ChronoUnit.SECONDS, Duration::getSeconds);
        durationUnitsMap.put(ChronoUnit.MILLIS, Duration::toMillis);
        durationUnitsMap.put(ChronoUnit.MICROS, duration -> (long) duration.getNano()/ChronoUnit.MICROS.getDuration().getNano());
        durationUnitsMap.put(ChronoUnit.NANOS, duration -> (long) duration.getNano());
    }
    
    private String getAmountStr(long amt, TemporalUnit unit) {
        String unitStr = unit.toString();
        if (amt <= 1 && unitStr.endsWith("s")) {
            unitStr = unitStr.substring(0, unitStr.length() - 1);
        }
        return String.format("%d %s", amt, unitStr);
    }
    
    public String totalDiffInWords(Temporal start, Temporal end) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);

        StringJoiner wordsJoiner = new StringJoiner(Constants.SPACE_STRING);
        for (TemporalUnit unit : allUnits) {
            try {
                long unitAmt = unit.between(start, end);
                if (unitAmt > 0) {
                    wordsJoiner.add(getAmountStr(unitAmt, unit));
                }
                start = start.plus(unitAmt, unit);
            } catch (UnsupportedTemporalTypeException e) {
            }
        }
        return wordsJoiner.toString();
    }

    public String totalDiffInWords(Duration duration) {
        Objects.requireNonNull(duration);

        StringJoiner wordsJoiner = new StringJoiner(Constants.SPACE_STRING);
        for (Map.Entry<TemporalUnit, Function<Duration, Long>> durationUnitEntry : durationUnitsMap.entrySet()) {
            TemporalUnit unit = durationUnitEntry.getKey();
            long unitAmount = durationUnitEntry.getValue().apply(duration);
            if (unitAmount > 0) {
                wordsJoiner.add(getAmountStr(unitAmount, unit));
                duration.minus(unitAmount, unit);
                if (duration.isZero() || duration.isNegative()) {
                    break;
                }
            }
        }
        return wordsJoiner.toString();
    }

    public String closestDiffInWords(Temporal start, Temporal end) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        for (TemporalUnit unit : allUnits) {
            try {
                long unitAmt = unit.between(start, end);
                if (unitAmt > 0) {
                    return String.format("About %s", getAmountStr(unitAmt, unit));
                }
            } catch (UnsupportedTemporalTypeException e) {
            }
        }
        return null;
    }

    @Override
    public void exerciseOutput() {
        Temporal start;
        Temporal end;
        start = Instant.now();
        end = Instant.now().plusSeconds(20);
        printfln("1. %s to %s = (%s) %s", start, end, closestDiffInWords(start, end), totalDiffInWords(start, end));

        start = LocalDateTime.now();
        end = LocalDateTime.now().plusHours(1).plusMinutes(3).plusSeconds(20);
        printfln("1. %s to %s = (%s) %s", start, end, closestDiffInWords(start, end), totalDiffInWords(start, end));

        start = LocalDateTime.now();
        end = LocalDateTime.now().plusDays(3).plusHours(1).plusMinutes(3).plusSeconds(20);
        printfln("1. %s to %s = (%s) %s", start, end, closestDiffInWords(start, end), totalDiffInWords(start, end));

        start = LocalDateTime.now();
        end = LocalDateTime.now().plusDays(12).plusHours(1).plusMinutes(3).plusSeconds(20);
        printfln("1. %s to %s = (%s) %s", start, end, closestDiffInWords(start, end), totalDiffInWords(start, end));

        start = LocalDateTime.now();
        end = LocalDateTime.now().plusDays(33).plusHours(1).plusMinutes(3).plusSeconds(20);
        printfln("1. %s to %s = (%s) %s", start, end, closestDiffInWords(start, end), totalDiffInWords(start, end));

        start = LocalDateTime.now();
        end = LocalDateTime.now().plusDays(95).plusHours(1).plusMinutes(3).plusSeconds(20);
        printfln("1. %s to %s = (%s) %s", start, end, closestDiffInWords(start, end), totalDiffInWords(start, end));
    }

    public static void main(String[] args) {
        (new DurationToWordConverter()).executeExercise();
    }

}
