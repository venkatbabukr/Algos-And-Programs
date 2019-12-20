# Datastructures-and-Algorithms
All my solutions and java code to the DS & Algos problems...

To execute a particular java class use:

```
mvn -q exec:java -Dexec.mainClass=<java class canonical name>
```
# Index of Programs
These are the different programs available:

## Algorithms
All different algorithm problems

### Simple
1. [NumberToWordConverter](src/main/java/com/venkat/algos/simple/NumberToWordConverter.java)
<details>
   <summary>Logic overview</summary>
   <p>
   <h6>For Metric System</h6>
   Have a method that coverts 3-digit number to string. Then call that method repeatedly for every 3-digit places and add suitable suffixes - Thousand, Million, Billion etc...
   </p>
</details>
2. [TimeDurationToWordConverter](src/main/java/com/venkat/algos/simple/TimeDurationToWordConverter.java)
3. [MathExt - Fibonacci etc...](src/main/java/com/venkat/algos/simple/MathExt.java)
4. [Spiral print matrix](src/main/java/com/venkat/algos/simple/MatrixSpiralPrinter.java)

### Recursion
5. [TowerOfHanoiSolver](src/main/java/com/venkat/algos/recursive/TowerOfHanoiSolver.java)
6. [GrayCodeBuilder](src/main/java/com/venkat/algos/recursive/GrayCodeBuilder.java)

### Bit string algorithms
6. [GrayCodeBuilder](src/main/java/com/venkat/algos/recursive/GrayCodeBuilder.java)
7. [BitFlipForMinMaxSolver](src/main/java/com/venkat/algos/bits/BitFlipForMinMaxSolver.java)
8. [BitFlipForAlternateSolver](src/main/java/com/venkat/algos/bits/BitFlipForAlternateSolver.java)

### Dynamic Programming
Both Backtracking, Branch & Bound

#### Backtracking
9. [NQueensSolver](src/main/java/com/venkat/algos/dp/NQueensSolver.java)

#### Branch & Bound
10. [NQueensSolver B&B](src/main/java/com/venkat/algos/dp/NQueensSolverBB.java)
11. [BracesBalancer](src/main/java/com/venkat/algos/dp/BracesBalancer.java)
    * [BracesBalancer - Java8](src/main/java/com/venkat/algos/dp/BracesBalancerJava8.java)
12. [SubSet Sum](src/main/java/com/venkat/algos/dp/SubSetSumSolver.java)
13. [Tour petrol pump](src/main/java/com/venkat/algos/dp/TourPetrolPumpSolver.java)

## Java
All Java problems

### Multi threading
14. [Odd & Even threads sequence printing problem](src/main/java/com/venkat/java/threads/OddEvenThreadPrinter.java)
15. [N-Threads sequence printing problem](src/main/java/com/venkat/java/threads/NThreadPrinter.java)
