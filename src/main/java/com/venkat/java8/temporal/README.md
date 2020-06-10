 ------------------------ 
| Java8 temporal package |
 ------------------------ 
1. [TemporalUnits - ChronoUnit](Exercise1TemporalUnit.java)
2. [TemporalField - ChronoField](Exercise2TemporalField.java)
3. [TemporalAmount](Exercise3TemporalAmount.java)
4. [TemporalAdjusters](Exercise4TemporalAdjuster.java)
5. [TemporalAccessor - Read only](Exercise5TemporalAccessor.java)
6. [Temporal - Perform airthmetic](Exercise6Temporal.java)

TemporalUnits - ChronoUnit
--------------------------
1. ChronoUnits with details:
    1. Nanos: {Duration: PT0.000000001S, Estimated: false, Date based: false, Time based: true}
    2. Micros: {Duration: PT0.000001S, Estimated: false, Date based: false, Time based: true}
    3. Millis: {Duration: PT0.001S, Estimated: false, Date based: false, Time based: true}
    4. Seconds: {Duration: PT1S, Estimated: false, Date based: false, Time based: true}
    5. Minutes: {Duration: PT1M, Estimated: false, Date based: false, Time based: true}
    6. Hours: {Duration: PT1H, Estimated: false, Date based: false, Time based: true}
    7. HalfDays: {Duration: PT12H, Estimated: false, Date based: false, Time based: true}
    8. Days: {Duration: PT24H, Estimated: true, Date based: true, Time based: false}
    9. Weeks: {Duration: PT168H, Estimated: true, Date based: true, Time based: false}
    10. Months: {Duration: PT730H29M6S, Estimated: true, Date based: true, Time based: false}
    11. Years: {Duration: PT8765H49M12S, Estimated: true, Date based: true, Time based: false}
    12. Decades: {Duration: PT87658H12M, Estimated: true, Date based: true, Time based: false}
    13. Centuries: {Duration: PT876582H, Estimated: true, Date based: true, Time based: false}
    14. Millennia: {Duration: PT8765820H, Estimated: true, Date based: true, Time based: false}
    15. Eras: {Duration: PT8765820000000H, Estimated: true, Date based: true, Time based: false}
    16. Forever: {Duration: PT2562047788015215H30M7.999999999S, Estimated: true, Date based: false, Time based: false}

2. IsoUnits with details:
    1. QuarterYears: Duration: PT2191H27M18S, Estimated: true, Date based: true, Time based: false
    2. WeekBasedYears: Duration: PT8765H49M12S, Estimated: true, Date based: true, Time based: false

3. TemporalUnit(ChronoUnit).addTo(LocalDate.now(), 2): 2020-06-12
4. TemporalUnit(ChronoUnit).between(LocalDate.now(), LocalDate.now().minusDays(2)): -2

TemporalField - ChronoField
---------------------------
1. ChronoFields with details:
    1. NanoOfSecond: Base unit: Nanos, Range unit: Seconds, Value range: 0 - 999999999
    2. NanoOfDay: Base unit: Nanos, Range unit: Days, Value range: 0 - 86399999999999
    3. MicroOfSecond: Base unit: Micros, Range unit: Seconds, Value range: 0 - 999999
    4. MicroOfDay: Base unit: Micros, Range unit: Days, Value range: 0 - 86399999999
    5. MilliOfSecond: Base unit: Millis, Range unit: Seconds, Value range: 0 - 999
    6. MilliOfDay: Base unit: Millis, Range unit: Days, Value range: 0 - 86399999
    7. SecondOfMinute: Base unit: Seconds, Range unit: Minutes, Value range: 0 - 59
    8. SecondOfDay: Base unit: Seconds, Range unit: Days, Value range: 0 - 86399
    9. InstantSeconds: Base unit: Seconds, Range unit: Forever, Value range: -9223372036854775808 - 9223372036854775807
    10. OffsetSeconds: Base unit: Seconds, Range unit: Forever, Value range: -64800 - 64800
    11. MinuteOfHour: Base unit: Minutes, Range unit: Hours, Value range: 0 - 59
    12. MinuteOfDay: Base unit: Minutes, Range unit: Days, Value range: 0 - 1439
    13. HourOfAmPm: Base unit: Hours, Range unit: HalfDays, Value range: 0 - 11
    14. ClockHourOfAmPm: Base unit: Hours, Range unit: HalfDays, Value range: 1 - 12
    15. HourOfDay: Base unit: Hours, Range unit: Days, Value range: 0 - 23
    16. ClockHourOfDay: Base unit: Hours, Range unit: Days, Value range: 1 - 24
    17. AmPmOfDay: Base unit: HalfDays, Range unit: Days, Value range: 0 - 1
    18. DayOfWeek: Base unit: Days, Range unit: Weeks, Value range: 1 - 7
    19. AlignedDayOfWeekInMonth: Base unit: Days, Range unit: Weeks, Value range: 1 - 7
    20. AlignedDayOfWeekInYear: Base unit: Days, Range unit: Weeks, Value range: 1 - 7
    21. DayOfMonth: Base unit: Days, Range unit: Months, Value range: 1 - 28/31
    22. DayOfYear: Base unit: Days, Range unit: Years, Value range: 1 - 365/366
    23. EpochDay: Base unit: Days, Range unit: Forever, Value range: -365249999634 - 365249999634
    24. AlignedWeekOfMonth: Base unit: Weeks, Range unit: Months, Value range: 1 - 4/5
    25. AlignedWeekOfYear: Base unit: Weeks, Range unit: Years, Value range: 1 - 53
    26. MonthOfYear: Base unit: Months, Range unit: Years, Value range: 1 - 12
    27. ProlepticMonth: Base unit: Months, Range unit: Forever, Value range: -11999999988 - 11999999999
    28. YearOfEra: Base unit: Years, Range unit: Forever, Value range: 1 - 999999999/1000000000
    29. Year: Base unit: Years, Range unit: Forever, Value range: -999999999 - 999999999
    30. Era: Base unit: Eras, Range unit: Forever, Value range: 0 - 1

2. IsoFields with details:
    1. DayOfQuarter: Base unit: Days, Range unit: QuarterYears, Value range: 1 - 90/92
    2. WeekBasedYear: Base unit: WeekBasedYears, Range unit: Forever, Value range: -999999999 - 999999999
    3. WeekOfWeekBasedYear: Base unit: Weeks, Range unit: WeekBasedYears, Value range: 1 - 52/53
    4. QuarterOfYear: Base unit: QuarterYears, Range unit: Years, Value range: 1 - 4

3. JulianFields with details:
    1. JulianDay: Base unit: Days, Range unit: Forever, Value range: -365240778574 - 365244221059
    2. RataDie: Base unit: Days, Range unit: Forever, Value range: -365242499999 - 365242499634

4. TemporalField(ChronoField).getFrom(LocalDateTime.now()): 3

TemporalAmount
--------------
1. get all units in the TemporalAmount(Duration) PT3H20M: [Seconds, Nanos]
2. get from TemporalAmount(Duration) PT3H20M using TimeUnit (Seconds): 12000 Secs
3. TemporalAmount(Duration) PT3H20M addTo(LocalDateTime.now()): 2020-06-10T18:20:13.057
4. TemporalAmount(Duration) PT3H20M subtractFrom(LocalDateTime.now()): 2020-06-10T11:40:13.058

TemporalAdjusters
-----------------
1. TemporalAdjusters:
    1. TemporalAdjusters.firstDayOfMonth().adjustInto(today): 2020-06-01
    2. today.with(TemporalAdjusters.lastDayOfMonth()): 2020-06-30
    3. today.with(TemporalAdjusters.firstDayOfNextMonth()): 2020-07-01
    4. today.with(TemporalAdjusters.firstDayOfYear()): 2020-01-01
    5. today.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)): 2020-06-01
    6. today.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)): 2020-06-13
    7. now.with(anonymous adjuster adding 3 half days): 2020-06-12T03:00:13.061

TemporalAccessor - Read only
----------------------------
1. get(<TemporalField>):
    1. Instant.now().get(NANO_OF_SECOND): 62000000
    2. LocalDate.now().get(DAY_OF_YEAR): 162
    3. LocalTime.now().get(HOUR_OF_DAY): 15
    4. LocalDateTime.now().get(AMPM_OF_DAY): 1
2. isSupported(<TemporalField>):
    1. Instant.isSupported(NANO_OF_SECOND): false
    2. LocalDate.isSupported(HOUR_OF_AMPM): false
    3. LocalTime.isSupported(YEAR): false
    4. LocalDateTime.isSupported(NANO_OF_DAY): true
3. query(<R>) query <TemporalUnit> using precision():
    1. Instant.query(precision()): Nanos
    2. LocalDate.query(precision()): Days
    3. LocalTime.query(precision()): Nanos
    4. LocalDateTime.query(precision()): Nanos

Temporal - Perform airthmetic
-----------------------------
1. plus():
    1. Instant.now().plus(Duration.ofHours(2L))): 2020-06-10T11:30:13.063Z
    2. LocalDate.now().plusDays(3): 2020-06-13
    3. LocalTime.now().plus(2, ChronoUnit.HALF_DAYS): 15:00:13.068
    4. LocalDateTime.now().plusHours(2L).plusMinutes(2L): 2020-06-10T17:02:13.068
2. minus():
    1. Instant.now().minus(Duration.ofHours(2L))): 2020-06-10T07:30:13.068Z
    2. LocalDate.now().minusDays(3): 2020-06-07
    3. LocalTime.now().minus(2, ChronoUnit.HALF_DAYS): 15:00:13.068
    4. LocalDateTime.now().minusHours(2L).minusMinutes(2L): 2020-06-10T12:58:13.068
3. with() same as exercise shown in TemporalAdjuster

