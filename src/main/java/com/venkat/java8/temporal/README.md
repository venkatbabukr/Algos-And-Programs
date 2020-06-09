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
	Nanos: Duration: PT0.000000001S, Estimated: false, Date based: false, Time based: true
	Micros: Duration: PT0.000001S, Estimated: false, Date based: false, Time based: true
	Millis: Duration: PT0.001S, Estimated: false, Date based: false, Time based: true
	Seconds: Duration: PT1S, Estimated: false, Date based: false, Time based: true
	Minutes: Duration: PT1M, Estimated: false, Date based: false, Time based: true
	Hours: Duration: PT1H, Estimated: false, Date based: false, Time based: true
	HalfDays: Duration: PT12H, Estimated: false, Date based: false, Time based: true
	Days: Duration: PT24H, Estimated: true, Date based: true, Time based: false
	Weeks: Duration: PT168H, Estimated: true, Date based: true, Time based: false
	Months: Duration: PT730H29M6S, Estimated: true, Date based: true, Time based: false
	Years: Duration: PT8765H49M12S, Estimated: true, Date based: true, Time based: false
	Decades: Duration: PT87658H12M, Estimated: true, Date based: true, Time based: false
	Centuries: Duration: PT876582H, Estimated: true, Date based: true, Time based: false
	Millennia: Duration: PT8765820H, Estimated: true, Date based: true, Time based: false
	Eras: Duration: PT8765820000000H, Estimated: true, Date based: true, Time based: false
	Forever: Duration: PT2562047788015215H30M7.999999999S, Estimated: true, Date based: false, Time based: false

2. IsoUnits with details:
	WeekBasedYears: Duration: PT8765H49M12S, Estimated: true, Date based: true, Time based: false
	QuarterYears: Duration: PT2191H27M18S, Estimated: true, Date based: true, Time based: false

3. TemporalUnit (ChronoUnit).addTo: 2020-06-11
4. TemporalUnit (ChronoUnit).between: -2

TemporalField - ChronoField
---------------------------
1. ChronoFields with details:
	NanoOfSecond: Base unit: Nanos, Range unit: Seconds, Value range: 0 - 999999999
	NanoOfDay: Base unit: Nanos, Range unit: Days, Value range: 0 - 86399999999999
	MicroOfSecond: Base unit: Micros, Range unit: Seconds, Value range: 0 - 999999
	MicroOfDay: Base unit: Micros, Range unit: Days, Value range: 0 - 86399999999
	MilliOfSecond: Base unit: Millis, Range unit: Seconds, Value range: 0 - 999
	MilliOfDay: Base unit: Millis, Range unit: Days, Value range: 0 - 86399999
	SecondOfMinute: Base unit: Seconds, Range unit: Minutes, Value range: 0 - 59
	SecondOfDay: Base unit: Seconds, Range unit: Days, Value range: 0 - 86399
	MinuteOfHour: Base unit: Minutes, Range unit: Hours, Value range: 0 - 59
	MinuteOfDay: Base unit: Minutes, Range unit: Days, Value range: 0 - 1439
	HourOfAmPm: Base unit: Hours, Range unit: HalfDays, Value range: 0 - 11
	ClockHourOfAmPm: Base unit: Hours, Range unit: HalfDays, Value range: 1 - 12
	HourOfDay: Base unit: Hours, Range unit: Days, Value range: 0 - 23
	ClockHourOfDay: Base unit: Hours, Range unit: Days, Value range: 1 - 24
	AmPmOfDay: Base unit: HalfDays, Range unit: Days, Value range: 0 - 1
	DayOfWeek: Base unit: Days, Range unit: Weeks, Value range: 1 - 7
	AlignedDayOfWeekInMonth: Base unit: Days, Range unit: Weeks, Value range: 1 - 7
	AlignedDayOfWeekInYear: Base unit: Days, Range unit: Weeks, Value range: 1 - 7
	DayOfMonth: Base unit: Days, Range unit: Months, Value range: 1 - 28/31
	DayOfYear: Base unit: Days, Range unit: Years, Value range: 1 - 365/366
	EpochDay: Base unit: Days, Range unit: Forever, Value range: -365249999634 - 365249999634
	AlignedWeekOfMonth: Base unit: Weeks, Range unit: Months, Value range: 1 - 4/5
	AlignedWeekOfYear: Base unit: Weeks, Range unit: Years, Value range: 1 - 53
	MonthOfYear: Base unit: Months, Range unit: Years, Value range: 1 - 12
	ProlepticMonth: Base unit: Months, Range unit: Forever, Value range: -11999999988 - 11999999999
	YearOfEra: Base unit: Years, Range unit: Forever, Value range: 1 - 999999999/1000000000
	Year: Base unit: Years, Range unit: Forever, Value range: -999999999 - 999999999
	Era: Base unit: Eras, Range unit: Forever, Value range: 0 - 1
	InstantSeconds: Base unit: Seconds, Range unit: Forever, Value range: -9223372036854775808 - 9223372036854775807
	OffsetSeconds: Base unit: Seconds, Range unit: Forever, Value range: -64800 - 64800

2. IsoFields with details:
	DayOfQuarter: Base unit: Days, Range unit: QuarterYears, Value range: 1 - 90/92
	WeekBasedYear: Base unit: WeekBasedYears, Range unit: Forever, Value range: -999999999 - 999999999
	WeekOfWeekBasedYear: Base unit: Weeks, Range unit: WeekBasedYears, Value range: 1 - 52/53
	QuarterOfYear: Base unit: QuarterYears, Range unit: Years, Value range: 1 - 4

3. JulianFields with details:
	JulianDay: Base unit: Days, Range unit: Forever, Value range: -365240778574 - 365244221059
	RataDie: Base unit: Days, Range unit: Forever, Value range: -365242499999 - 365242499634

4. TemporalField (ChronoField).getFrom: 4

TemporalAmount
--------------
1. get all units within the amount: [Seconds, Nanos]
2. getAmount using TimeUnit (Seconds): 12000 Secs
3. addTo: 2020-06-09T19:42:30.466
4. subtractFrom: 2020-06-09T13:02:30.466

TemporalAdjusters
-----------------
1. TemporalAdjusters:
    1. TemporalAdjusters.firstDayOfMonth().adjustInto(today): 2020-06-01
    2. today.with(TemporalAdjusters.lastDayOfMonth()): 2020-06-30
    3. today.with(TemporalAdjusters.firstDayOfNextMonth()): 2020-07-01
    4. today.with(TemporalAdjusters.firstDayOfYear()): 2020-01-01
    5. today.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)): 2020-06-01
    6. today.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)): 2020-06-13
    7. now.with(anonymous adjuster adding 3 half days): 2020-06-11T04:22:30.471

TemporalAccessor - Read only
----------------------------
1. get(<TemporalField>):
    1. Instant.now().get(NANO_OF_SECOND): 472000000
    2. LocalDate.now().get(DAY_OF_YEAR): 161
    3. LocalTime.now().get(HOUR_OF_DAY): 16
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
    1. Instant.now().plus(Duration.ofHours(2L))): 2020-06-09T12:52:30.473Z
    2. LocalDate.now().plusDays(3): 2020-06-12
    3. LocalTime.now().plus(2, ChronoUnit.HALF_DAYS): 16:22:30.480
    4. LocalDateTime.now().plusHours(2L).plusMinutes(2L): 2020-06-09T18:24:30.480
2. minus():
    1. Instant.now().minus(Duration.ofHours(2L))): 2020-06-09T08:52:30.480Z
    2. LocalDate.now().minusDays(3): 2020-06-06
    3. LocalTime.now().minus(2, ChronoUnit.HALF_DAYS): 16:22:30.480
    4. LocalDateTime.now().minusHours(2L).minusMinutes(2L): 2020-06-09T14:20:30.480
3. with() same as exercise shown in TemporalAdjuster

