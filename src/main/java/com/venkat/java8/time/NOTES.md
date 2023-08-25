# Important Packages

* Main
    1. [***TemporalAccessor***](#javatimetemporaltemporalaccessor): Framework level, Read only access to a temporal object
         * All [***Temporal***](#javatimetemporaltemporal)
         * Month
         * MonthDay
    2. [***Temporal***](#javatimetemporaltemporal): Framework-level interface for read-write access to a temporal object
         * [LocalDate](javatimelocaldate)
         * [LocalTime](javatimelocaltime)
         * [LocalDateTime](javatimelocaldatetime)
         * [Instant](javatimeinstant)
         * ChronoLocalDate
             * HijrahDate
             * JapaneseDate
             * ThaiBuddhistDate
             * MinguoDate
    3. ***TemporalAdjuster***
         * DayOfWeek: MONDAY, TUESDAY, WEDNESDAY...
         * MONTH: JANUARY, FEBRUARY, MARCH, APRIL...
         * MonthDay
         * 
    5. ***TemporalAmount***:
         * Duration
         * ChronoPeriod
             * Period
    6. ***TemporalField***
         * ChronoField: YEAR, MONTH_OF_YEAR, DAY, HOUR_OF_DAY, MINUTE_OF_DAY, SECOND_OF_DAY
         * IsoFields: DAY_OF_QUARTER, QUARTER_OF_YEAR etc...
    7. ***TemporalUnit***
* chrono
    1. Chronology
         * IsoChronology
         * HijrahChronology
         * JapaneseChronology
         * ThaiBuddhistChronology
    2. ChronoLocalDate
    3. ChronoLocalDateTime
    4. ChronoPeriod
* Queries
    1. TemporalQuery
    2. TemporalQueries
* Data objects
    1. ValueRange
    2. Duration
    3. Period

# Main
### java.time.temporal.TemporalAccessor
* isSupported(TemporalField)
* range(TemporalField)
* get(TemporalField)
* getLong(TemporalField)
* query(TemporalQuery<R>)
### java.time.temporal.Temporal
* isSupported(TemporalUnit)
* with(TemporalAdjuster)
    * 
* plus/minus(TemporalAmount)
    * LocalDateTime.now().plus(Duration.of(20, ChronoUnit.DAYS));
* long until(Temporal t2, TemporalUnit u)
    * Year.of(2023).until(Year.of(2024), ChronoUnit.YEARS/DECADES/CENTURIES/MILLENIA/))
### java.time.LocalDate
* now()
* of(int year, Month, int dayOfMonth)
* from(TemporalAccessor)
### java.time.LocalDateTime
* now()
* of(int year, Month, int dayOfMonth)
* from(TemporalAccessor)
### java.time.LocalDateTime
* now()
* of(int year, Month, int dayOfMonth)
* from(TemporalAccessor)
### java.time.Instant
* now()
* of(int year, Month, int dayOfMonth)
* from(TemporalAccessor)
# Chrono
### java.time.chrono.JapaneseChronology
* JapaneseChronology.INSTANCE.date(2023, 10, 1)
### java.time.chrono.JapaneseDate
* JapaneseDate.now()
# Data objects
### java.time.temporal.ValueRange
* ValueRange daysInFeb2023 = LocalDate.of(2023, 2, 1).range(ChronoField.DAY_OF_MONTH) => Returns ValueRange(1, 28) as the TemporalField queried is DAY_OF_MONTH and month is february and year 2023 is not leap year.
* daysInFeb2023.isValidValue(29) => Results in false
