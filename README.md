# Table Of Content
   * [Overview](#overview)
   * [Index of Programs](#index-of-programs)
     * [Algorithms](#algorithms)
       * [Recursion](#recursion)
       * [Greedy](#greedy)
       * [Dynamic Programming](#dynamic-programming)
         * [Backtracking](#backtracking)
         * [Branch & Bound](#branch--bound)
       * [Simple](#simple)
       * [Bit Logic Problems](#bit-logic-problems)
       * [Array Problems](#array-problems)
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
   <details>
      <summary>Algorithm logic</summary>
      <p>
        <ul>
          <li>if (diskNumber = 1)<code>print("Move %d from %s to %s", diskNumber, fromPole, toPole</li></code>
          <li> else
            <ul>
              <li><code>solve(diskNumber-1, fromPole, intermediaryPole, toPole)</code></li>
              <li><code>print("Move %d from %s to %s", diskNumber, fromPole, toPole)</code></li>
              <li><code>solve(diskNumber-1, intermediaryPole, toPole, fromPole)</code></li>
            </ul>
          </li>
        </ul>
      </p>
   </details>
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
   <details>
      <summary>Algorithm logic</summary>
      <p>
        <ul>
          <li><code>totalTourLength = arr.length;</code></li>
          <li> Outer for: <code>for (tourStart = 0 ; tourStart < arr.length; /* Donot increment tourStart here */)</code></li>
          <ul>
            <li> Inner for: <code>for (tourEnd = (tourStart + 1) % totalTourLength,<p>
                                         remGas = arr[tourStart].getGas - arr[tourStart].getNextDist,<p>
                                         totalPointsCovered = 1; remGas > 0 && tourPointsCovered < totalTourLength;
                 totalPointsCovered++, tourEnd = (tourEnd + 1) % totalTourLength)</code></li>
            <ul>
              <li><code>remGas = arr[tourEnd].getGas - arr[tourEnd].getNextDist;</code></li>
            </ul>
          </ul>
          <li>At the end<code>return -1;</code></li>
         </ul>
      </p>
   </details>
4. [FindArrayMajorityElement](src/main/java/com/venkat/algos/greedy/MajorityElementsSolver.java)
   <details>
      <summary>Algorithm logic</summary>
      <p>
         <div>Ref URL: https://www.geeksforgeeks.org/majority-element/</div>
         <div>Use Moore's voting algorithm to get in O(N) time.</div>
         <ul>
             <li>
                 In the first pass, find potential majority element using simple heuristic logic:<p>
                 <code>
                     majorityElementIdx = 1, majorityElementCount = 1<p>
                     for (i in 1..arr.length)<p>
                         if (arr[i] == arr[i-1]) i is potential candidate, majorityElementCount++<p>
                         else majorityElementCount--<p>
                         <p>
                         if (majorityElementCount == 0) set majorityElementIdx = i + 1, majorityElementCount = 0
                 </code>
             </li>
             <li>Iterate the array again to get count of <code>arr[majorityElementIdx]</code></li>
         </ul>
      </p>
   </details>

### Dynamic Programming

Memoization
#### Sequences & Subsequences
5. [Longest Common Substring](src/main/java/com/venkat/algos/dp/matrix/LCSSubStringSolver.java)
   <details>
      <summary>DP logic</summary>
      <p>
          <ul>
              <li>
                  <b>Sub problem/Memoization definition: </b><code>Longest substr@(i, j) = s1[i] == s2[j] ? Longest substr(i-1)(j-1) + 1 : 0!</code>
              </li>
              <li>
                  <b>Reducion logic: </b>Find index(i) in the memoized array which has largest value. <code>s1.substring(i - longestVal, longestVal)</code> is the longest common substring...
              </li>
          </ul>
      </p>
   </details>
6. [Longest Common Subsequence](src/main/java/com/venkat/algos/dp/matrix/LCSStringSolver.java)
7. [Longest Increasing Subsequence](src/main/java/com/venkat/algos/dp/matrix/LISSolver.java)
8. [Distinct Sub Sequences](src/main/java/com/venkat/algos/dp/matrix/DSSolver.java)

Both Backtracking, Branch & Bound
#### Backtracking
9. [NQueensSolverBacktracking](src/main/java/com/venkat/algos/dp/nqueens/NQueensSolverBacktracking.java)

#### Branch & Bound
10. [NQueensSolver B&B](src/main/java/com/venkat/algos/dp/nqueens/NQueensSolverBB.java)
11. [BracesBalancer](src/main/java/com/venkat/algos/dp/BracesBalancer.java)
    * [BracesBalancer - Java8](src/main/java/com/venkat/algos/dp/BracesBalancerJava8.java)
12. [SubSet Sum](src/main/java/com/venkat/algos/dp/SubSetSumSolver.java)

### Leetcode
13. [LinkedListSum](src/main/java/com/venkat/algos/leet/LinkedListSum.java)

### Simple
14. [NumberToWordConverter](src/main/java/com/venkat/algos/simple/NumberToWordConverter.java)
    <details>
       <summary>Algorithm logic</summary>
       <p>
          <ul>
              <li><b>For Million system:</b> Have a method that converts <a href="src/main/java/com/venkat/algos/simple/NumberToWordConverter.java#L69-L92">3-digit number to words</a>. Then, we can <a href="src/main/java/com/venkat/algos/simple/NumberToWordConverter.java#L124-L138">repeatedly call this method for every three digits and keep adding suitable suffixes</a> like - Thousand, Million, Billion etc...
              </li>
          </ul>
       </p>
    </details>

15. [TimeDurationToWordConverter](src/main/java/com/venkat/algos/simple/TimeDurationToWordConverter.java)
16. [MathExt - Fibonacci etc...](src/main/java/com/venkat/algos/simple/MathExt.java)
17. [Spiral print matrix](src/main/java/com/venkat/algos/simple/MatrixSpiralPrinter.java)
18. [Partition array into Even & Odd](src/main/java/com/venkat/algos/simple/DutchOddEvenPartitioner.java)
    * Partition without sort
    * Partition with sort

### Bit Logic Problems
19. [GrayCodeBuilder](src/main/java/com/venkat/algos/recursive/GrayCodeBuilder.java)
20. [BitFlipForMinMaxSolver](src/main/java/com/venkat/algos/bits/BitFlipForMinMaxSolver.java)
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
21. [BitFlipForAlternateSolver](src/main/java/com/venkat/algos/bits/BitFlipForAlternateSolver.java)
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

### Array Problems
Approaches
  * Using multiple pointers
    * Dutch Odd-Even partition
      <details>
        <p>
          <ul>
            <li> Keep leftmostOdd (Starting from 0) and rightmostEven (Starting from n-1)</li>
            <li> Move the pointers until the odd/even condition is met.</li>
            <li> When condition not met - it means - leftmostOdd has an even number and rightmostEven has odd number</li>
            <li> Just swap and continue!</li>
          </ul>
        </p>
      </details>
    * Minimum jumps required problem
      <details>
        <p>
          Will add details here...
        </p>
      </details>
  * Window based solutions (Keep the window pointers moving)
    * Subarray sum: Find subarray equaling given Sum (s)
    * Maximum sum subsequence: Find subarray
  * Mathematical approach
    * Find missing number in an array of 1 to N-1
    * Find two repeating numbers in array of 1 to N-1 (Use (a-b)^2 = (a+b)^2 - 4*a*b
  * Use extra space
    * Find 2 numbers in array whose sum = s
  * Inline value replacement problems
    * Find duplicates in array having numbers 0 to N-1
      <details>
        <p>
          <ul>
            <li> Iterate over each element in array and set arr[ele % n] += n</li>
            <li> In second iteration of array now check if arr[i] / n > 1. If yes, then i occurs more than once!</li>
          </ul>
        </p>
      </details>
    * Implement stack with getMin() in O(1)
      <details>
        <p>
          <ul>
            <li> Keep currentMin variable to point to the currentMinimum val</li>
            <li> During every push(x)
              <ul>
                <li> if (x >= currentMin) just push x</li>
                <li> if (x < currentMin) Push 2 * x - currentMin, Set currentMin=x. Note: Since x < currentMin, 2*x-currentMin (x + (x - currentMin)) will be < x - The new currentMin </li>
              </ul>
            </li>
            <li> During pop()
              <ul>
                <li> if num >= currentMin - return num</li>
                <li> if num < currentMin, Return currentMin (which is x), update currentMin = 2 * currentMin - num (Because 2 * currentMin - num = 2 * currentMin (x) - (2 * x - previousMin)</li>
              </ul>
            </li>
          </ul>
        </p>
      </details>


## Java
All Java problems

### Multi threading
22. [Odd & Even threads sequence printing problem](src/main/java/com/venkat/java/threads/OddEvenThreadPrinter.java)
23. [N-Threads sequence printing problem](src/main/java/com/venkat/java/threads/NThreadPrinter.java)
