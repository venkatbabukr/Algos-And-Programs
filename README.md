# Table Of Content
   * [Overview](#overview)
   * [Index of Programs](#index-of-programs)
     * [Algorithms](#algorithms)
       * [Simple](#simple)
       * [Recursion](#recursion)
       * [Bit Logic Problems](#bit-logic-problems)
       * [Dynamic Programming](#dynamic-programming)
         * [Backtracking](#backtracking)
         * [Branch & Bound](#branch--bound)
     * [Java](#java)
       * [Multi threading](#multi-threading)
# Overview
This project contains all my solutions to DS & Algos problems... Almost all of them are codede in Java 

To execute a particular java class use:

```
mvn -q exec:java -Dexec.mainClass=<java class canonical name>
```
# Index of Programs
These are the different programs available:

## Algorithms
All different algorithm problems

### Recursion
1. [TowerOfHanoiSolver](src/main/java/com/venkat/algos/recursive/TowerOfHanoiSolver.java)
2. [GrayCodeBuilder](src/main/java/com/venkat/algos/recursive/GrayCodeBuilder.java)
   <details>
      <summary>Algorithm logic</summary>
      <p>
         <ul>
             <li>if numBits = 1, <code>return [0, 1]</code></li>
             <li>else (for all numBits > 1)
                 <ul>
                    <li>L for (n-1) = Get GrayCode list for (numBits - 1)
                    <li>L for (n) = <code>['0' + L for (n-1), '1' + reverse(L for (n-1))]</code>
                </ul>
            </li>
         </ul>
      </p>
   </details>

### Greedy
3. [TourPetrolPumps](src/main/java/com/venkat/algos/greedy/TourPetrolPumpSolverOptimized.java)
4. [FindArrayMajorityElement](src/main/java/com/venkat/algos/greedy/MajorityElementsSolver.java)

### Dynamic Programming

Memoization
#### Longest Common Subsequence
5. [LongestCommonSubsequence] (src/main/java/com/venkat/algos/dp/lcs/LCSStringSolver.java)
6. [LongestCommonSubstring] (src/main/java/com/venkat/algos/dp/lcs/LCSSubStringSolver.java)
7. [LongestIncreasingSubsequence] (src/main/java/com/venkat/algos/dp/lcs/LISSolver.java)

Both Backtracking, Branch & Bound
#### Backtracking
8. [NQueensSolverBacktracking](src/main/java/com/venkat/algos/dp/nqueens/NQueensSolverBacktracking.java)

#### Branch & Bound
9. [NQueensSolver B&B](src/main/java/com/venkat/algos/dp/nqueens/NQueensSolverBB.java)
10. [BracesBalancer](src/main/java/com/venkat/algos/dp/BracesBalancer.java)
    * [BracesBalancer - Java8](src/main/java/com/venkat/algos/dp/BracesBalancerJava8.java)
11. [SubSet Sum](src/main/java/com/venkat/algos/dp/SubSetSumSolver.java)

### Leetcode
12. [LinkedListSum] (src/main/java/com/venkat/algos/leet/LinkedListSum.java)

### Simple
13. [NumberToWordConverter](src/main/java/com/venkat/algos/simple/NumberToWordConverter.java)
   <details>
      <summary>Algorithm logic</summary>
      <p>
         <ul>
         <li><b>For Million system:</b> Have a method that converts <a href="src/main/java/com/venkat/algos/simple/NumberToWordConverter.java#L69-L92">3-digit number to words</a>. Then, we can <a href="src/main/java/com/venkat/algos/simple/NumberToWordConverter.java#L124-L138">repeatedly call this method for every three digits and keep adding suitable suffixes</a> like - Thousand, Million, Billion etc...</li>
         </ul>
      </p>
   </details>

14. [TimeDurationToWordConverter](src/main/java/com/venkat/algos/simple/TimeDurationToWordConverter.java)
15. [MathExt - Fibonacci etc...](src/main/java/com/venkat/algos/simple/MathExt.java)
16. [Spiral print matrix](src/main/java/com/venkat/algos/simple/MatrixSpiralPrinter.java)
17. [Partition array into Even & Odd](src/main/java/com/venkat/algos/simple/DutchOddEvenPartitioner.java)
    * Partition without sort
    * Partition with sort

### Bit Logic Problems
18. [GrayCodeBuilder](src/main/java/com/venkat/algos/recursive/GrayCodeBuilder.java)
19. [BitFlipForMinMaxSolver](src/main/java/com/venkat/algos/bits/BitFlipForMinMaxSolver.java)
   <details>
      <summary>Algorithm logic</summary>
      <p>
        The problem is to find distance of given bit-sequence to closest of the two - All zeeros (000...) or All ones (111...). Given below is a simple heuristic to follow:
        <ul>
          <li>Check the left most bit: <code>retainBit</code> - If Zero, then move towards flipping remaining to all Zeros(000...). If One, then move towards flipping remaining to all Ones(111...)</li>
          <li>For each of the reamining bit positions - If they are not same as <code>retainBit</code>, keep flipping from that position onwards</li>
        </ul>
      </p>
   </details>
20. [BitFlipForAlternateSolver](src/main/java/com/venkat/algos/bits/BitFlipForAlternateSolver.java)
    <details>
       <summary>Algorithm logic</summary>
       <p>
         <ol>
           <li>Only two alternate sequences are possible: ZERO_ONE (01) or ONE_ZERO (10).</li>
           <li>So, pick one of the sequence - say ZERO_ONE and find the distance of given bit string to this alternating sequence (0101...) i.e. how many bits in given bit string need to be flipped to match the alternating sequence (010101...).
             <ul>
               <li>This can be done by <a href="src/main/java/com/venkat/algos/bits/BitFlipForAlternateSolver.java#L64-L72">map-reduce logic Sum[pos=0-len](bitStrArray[pos] ^ templateStrArray[pos % 2])</a>.</li>
             </ul>
           </li>
           <li>Distance to other sequence - ONE_ZERO = bitStr.length - above calculated distance</li>
           <li>Pick the <a href="src/main/java/com/venkat/algos/bits/BitFlipForAlternateSolver.java#L104-L122">closest from above two and solve</a></li>
         </ol>
       </p>
    </details>

## Java
All Java problems

### Multi threading
21. [Odd & Even threads sequence printing problem](src/main/java/com/venkat/java/threads/OddEvenThreadPrinter.java)
22. [N-Threads sequence printing problem](src/main/java/com/venkat/java/threads/NThreadPrinter.java)
