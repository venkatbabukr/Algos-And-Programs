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
1=> Array using toArray(): [I@6002014d, [1, 2, 3, 4, 5, 6, 7, 8, 9]
2=> Transform to double using map(): [12.0, 39.0, 2.0, 1.0, 8.0, 22.0, 84.0, 94.0, 1.0, 4.0, 2.0, 10.0]
3=> Sorted list using sequential() & sorted(): [1, 1, 2, 2, 4, 8, 10, 12, 22, 39, 84, 94]

Stream Aggregating
------------------
1=> Count using count(): 12
2=> Sum using reduce(): Optional[279]
3=> Matrix Sum using flatMap() & reduce(): Optional[279]
4=> Max using max(): Optional[94]

Stream Filtering
----------------
1=> Numbers divisible by 3: [12, 39, 84]
2=> Distinct numbers: [12, 39, 2, 1, 8, 22, 84, 94, 4, 10]

Stream Iteration & Predicates
-----------------------------
1=> Iterate using forEach: [12] [39] [2] [1] [8] [22] [84] [94] [1] [4] [2] [10] 
2=> First number using findFirst(): Optional[12]
3=> Any number using findAny(): Optional[12]
4=> Check if all are positive, negative using allMatch(): true, false
5=> Check if any even number, negative using anyMatch(): true, false
6=> Check if no negatives using noneMatch(): true

Stream Generation & simple Concat
---------------------------------
1. Stream.of(): [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
2. 5 even numbers using Stream.iterate() and limit(): [0, 2, 4, 6, 8]
3. 5 powers of using Stream.iterate() and limit(): [1, 2, 4, 8, 16]
4. 5 random UUIDs using generate(): [37ae2a91-cd56-4f36-8f21-acc2fd089663, 6e7bffb2-fcc6-4b8f-a35c-2d3c63936192, 85077517-8f6f-4f97-af19-dae0deb5a4c6, 0cdca819-276d-4749-a973-547e3bad9eea, 21f60ef8-f663-403a-bda2-981db111d6f5]
5. Integer stream between (3, 13) using range(): [3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
6. Iterate on array using IntStream.range(): Arr[0]=12, Arr[1]=39, Arr[2]=2, Arr[3]=1, Arr[4]=8, Arr[5]=22, Arr[6]=84, Arr[7]=94, Arr[8]=1, Arr[9]=4, Arr[10]=2, Arr[11]=10
7. Concatenate two streams using Stream.concat(): [1, 2, 3, 4, 5, 6]
8. Concatenate multiple streams using Stream.of() and then flatMap(): [1, 2, 3, 4, 5, 6, 7, 8, 9]

OOTB Simple Streams
-------------------
1. IntStream.summaryStatistics(): IntSummaryStatistics{count=9, sum=45, min=1, average=5.000000, max=9}
2. LongStream.summaryStatistics(): LongSummaryStatistics{count=9, sum=45, min=1, average=5.000000, max=9}
3. DoubleStream.summaryStatistics(): DoubleSummaryStatistics{count=9, sum=45.000000, min=1.000000, average=5.000000, max=9.000000}

