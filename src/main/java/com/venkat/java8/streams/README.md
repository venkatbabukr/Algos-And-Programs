 --------------- 
| Java8 Streams |
 --------------- 
1. [Stream Transforming](Exercise1StreamTransforming.java)
2. [Stream Aggregating](Exercise2StreamAggregating.java)
3. [Stream Filtering](Exercise3StreamFiltering.java)
4. [Stream Iteration & Predicates](Exercise4StreamIterationAndPredicates.java)
5. [Stream Generation & simple Concat](Exercise5StreamGenerationAndConcat.java)
6. [OOTB Simple Streams](Exercise6OOTBStreams.java)

Stream Transforming
-------------------
1. Array using toArray(): [I@306a30c7, [1, 2, 3, 4, 5, 6, 7, 8, 9]
2. Transform to double using map(): [12.0, 39.0, 2.0, 1.0, 8.0, 22.0, 84.0, 94.0, 1.0, 4.0, 2.0, 10.0]
3. Sorted list using sequential() & sorted(): [1, 1, 2, 2, 4, 8, 10, 12, 22, 39, 84, 94]

Stream Aggregating
------------------
1. Count using count(): 12
2. Sum using reduce(): Optional[279]
3. Matrix Sum using flatMap() & reduce(): Optional[279]
4. Max using max(): Optional[94]

Stream Filtering
----------------
1. Numbers divisible by 3: [12, 39, 84]
2. Distinct numbers: [12, 39, 2, 1, 8, 22, 84, 94, 4, 10]

Stream Iteration & Predicates
-----------------------------
1. Iterate using forEach: [12] [39] [2] [1] [8] [22] [84] [94] [1] [4] [2] [10] 
2. First number using findFirst(): Optional[12]
3. Any number using findAny(): Optional[12]
4. Check if all are positive, negative using allMatch(): true, false
5. Check if any even number, negative using anyMatch(): true, false
6. Check if no negatives using noneMatch(): true

Stream Generation & simple Concat
---------------------------------
1. Stream.of(): [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
2. 5 even numbers using Stream.iterate() and limit(): [0, 2, 4, 6, 8]
3. 5 powers of using Stream.iterate() and limit(): [1, 2, 4, 8, 16]
4. 5 random UUIDs using generate(): [f92f2573-c23b-4c82-a489-839efc56360e, 2f00296a-0c93-4b0c-93f0-235ab87f6c50, e917f2db-266c-4b9b-b0ff-930dc3e05e76, 0baea74e-c088-4a38-9735-c79a09ba96cb, 7dd81376-a38d-4c27-b6f6-8ed5827cfdab]
5. Integer stream between (3, 13) using range(): [3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
6. Iterate on array using IntStream.range(): Arr[0]=12, Arr[1]=39, Arr[2]=2, Arr[3]=1, Arr[4]=8, Arr[5]=22, Arr[6]=84, Arr[7]=94, Arr[8]=1, Arr[9]=4, Arr[10]=2, Arr[11]=10
7. Concatenate two streams using Stream.concat(): [1, 2, 3, 4, 5, 6]
8. Concatenate multiple streams using Stream.of() and then flatMap(): [1, 2, 3, 4, 5, 6, 7, 8, 9]

OOTB Simple Streams
-------------------
1. IntStream.summaryStatistics(): IntSummaryStatistics{count=9, sum=45, min=1, average=5.000000, max=9}
2. LongStream.summaryStatistics(): LongSummaryStatistics{count=9, sum=45, min=1, average=5.000000, max=9}
3. DoubleStream.summaryStatistics(): DoubleSummaryStatistics{count=9, sum=45.000000, min=1.000000, average=5.000000, max=9.000000}

