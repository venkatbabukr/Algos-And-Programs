package com.venkat.algos.simple;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.Comparator;

public class TimeDurationToWordConverter {
    
    private final ChronoUnit[] orderedChronoUnits = ChronoUnit.values();
    
    public TimeDurationToWordConverter() {
        Arrays.sort(orderedChronoUnits, Comparator.comparing(ChronoUnit::getDuration).reversed());
    }
    
    public String durationToWord(Temporal t1, Temporal t2) {
        int startIdx;
        for (startIdx = 0 ; startIdx < orderedChronoUnits.length && (!orderedChronoUnits[startIdx].isSupportedBy(t1) || orderedChronoUnits[startIdx].between(t1, t2) <= 0) ; startIdx++);

        StringBuilder wordBuilder = new StringBuilder();
        for (int idx = startIdx ; idx < orderedChronoUnits.length && orderedChronoUnits[idx].isSupportedBy(t1) ; idx++) {
            ChronoUnit cu = orderedChronoUnits[idx];
            long diffQty = cu.between(t1, t2);
            if (diffQty <= 0) {
                break;
            }
            wordBuilder.append(diffQty + " " + cu.toString()).append(" ");
            t1 = t1.plus(diffQty, cu);
        }
        return wordBuilder.length() > 0 ? wordBuilder.toString().trim() : null;
    }

    public static void main(String[] args) {
        TimeDurationToWordConverter converter = new TimeDurationToWordConverter();

        LocalDate today = LocalDate.now();
        System.out.println(converter.durationToWord(today, today.plusDays(40)));
        
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next = now.plusDays(20).plusHours(15).plusMinutes(20).plusSeconds(10);
        System.out.println(converter.durationToWord(now, next));
    }

}
