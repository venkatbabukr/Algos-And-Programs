 ------------------------- 
| Java8 Stream Collectors |
 ------------------------- 
1. [Transforming Collectors](Exercise1TransformingCollectors.java)
2. [Aggregating Collectors](Exercise2AggregatingCollectors.java)
3. [Grouping Collectors](Exercise3GroupingCollectors.java)
4. [Partitioning Collectors](Exercise4PartitioningCollectors.java)
5. [AndThen Collectors](Exercise5AndThenCollectors.java)
6. [Word Count Solver](GroupingCollectorWordCountSolver.java)

Transforming Collectors
-----------------------
1. List using toList(): [12, 39, 2, 1, 8, 22, 84, 94, 1, 4, 2, 10]
2. Set using toSet(): [1, 2, 84, 4, 22, 39, 8, 10, 12, 94]
3. Sorted Set using toCollection(): [1, 2, 4, 8, 10, 12, 22, 39, 84, 94]
4. String using joining(): Begin String->>"12, 39, 2, 1, 8, 22, 84, 94, 1, 4, 2, 10"<<-End String
5. Transform to negative numbers using mapping(): [-12, -39, -2, -1, -8, -22, -84, -94, -1, -4, -2, -10]
6. Map<number, it's double> using toMap(): {1=1.0, 2=2.0, 4=4.0, 8=8.0, 10=10.0, 12=12.0, 22=22.0, 39=39.0, 84=84.0, 94=94.0}
7. Meals List . Map by ID using toMap(): {1=Toast/Sandwitch[CONTINENTAL/BREAKFAST], 2=Tuna Fish[NONVEG/DINNER], 3=Doritos[CONTINENTAL/SNACK], 4=Steamed Rice[ASIANVEG/LUNCH], 5=Idly Sambar[ASIANVEG/BREAKFAST]}
8. Meals List => Map by name using toMap(): {Doritos=Doritos[CONTINENTAL/SNACK], Idly Sambar=Idly Sambar[ASIANVEG/BREAKFAST], Steamed Rice=Steamed Rice[ASIANVEG/LUNCH], Toast/Sandwitch=Toast/Sandwitch[CONTINENTAL/BREAKFAST], Tuna Fish=Tuna Fish[NONVEG/DINNER]}

Aggregating Collectors
----------------------
1. Count using counting(): 12
2. Sum using summingInt(): 279
3. Average using averagingInt(): 23.25
4. Product using reducing(): Optional[981221376]
5. Summary using summarizingInt(): IntSummaryStatistics{count=12, sum=279, min=1, average=23.250000, max=94}
6. Maximum using maxBy(): Optional[94]

Grouping Collectors
-------------------
1. Meals list => Group by time:
{LUNCH=[Steamed Rice[ASIANVEG/LUNCH]], SNACK=[Doritos[CONTINENTAL/SNACK]], DINNER=[Tuna Fish[NONVEG/DINNER]], BREAKFAST=[Toast/Sandwitch[CONTINENTAL/BREAKFAST], Idly Sambar[ASIANVEG/BREAKFAST]]}

2. Meals list => Nested grouping by <type, time>:
{CONTINENTAL={BREAKFAST=[Toast/Sandwitch[CONTINENTAL/BREAKFAST]], SNACK=[Doritos[CONTINENTAL/SNACK]]}, ASIANVEG={BREAKFAST=[Idly Sambar[ASIANVEG/BREAKFAST]], LUNCH=[Steamed Rice[ASIANVEG/LUNCH]]}, NONVEG={DINNER=[Tuna Fish[NONVEG/DINNER]]}}

Partitioning Collectors
-----------------------
1. Odd/Even partition: {false=[39, 1, 1], true=[12, 2, 8, 22, 84, 94, 4, 2, 10]}
2. Odd/Even partition set using downstream toSet(): {false=[1, 39], true=[2, 84, 4, 22, 8, 10, 12, 94]}
3. Odd/Even partition sorted set using downstream toCollection(): {false=[1, 39], true=[2, 4, 8, 10, 12, 22, 84, 94]}

AndThen Collectors
------------------
1. Odd/Even Partition map with {true: <even numbers>, false: <odd numbers>}: {false=[39, 1, 1], true=[12, 2, 8, 22, 84, 94, 4, 2, 10]}

Word Count Solver
-----------------
1. Simple words count in inputs/WordCountSolverInputFile.txt  = {apple=2, banana=1, grapes=1, mango=1, orange=3, peach=1}
2. Words count inside poem inputs/JackAndJillPoem.txt = {a=2, after=2, and=8, as=2, broke=2, brown=1, came=2, caper=1, could=1, crown=2, dame=1, did=1, dob=1, down=2, fast=1, fell=2, fetch=2, got=1, he=1, hill=2, his=3, home=1, jack=5, jill=4, nob=1, of=2, old=1, pail=2, paper=1, patched=1, the=2, to=3, trot=1, tumbling=2, up=3, vinegar=1, water=2, went=2, who=1, with=1}

