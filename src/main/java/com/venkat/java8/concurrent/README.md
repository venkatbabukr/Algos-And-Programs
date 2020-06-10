 ------------------- 
| Java8 Concurrency |
 ------------------- 
1. [Completable Future](Exercise1CompletableFuture.java)

Completable Future
------------------
1. Creating completable futures
    1. Using new: {Obj: java.util.concurrent.CompletableFuture@6d9c638[Not completed], isDome: false}
    2. Runnables [com.venkat.java8.concurrent.Exercise1CompletableFuture$$Lambda$148/1973538135@626b2d4a, com.venkat.java8.concurrent.Exercise1CompletableFuture$$Lambda$149/1023487453@5e91993f] (runAsync) -> CFs [java.util.concurrent.CompletableFuture@1c4af82c[Not completed], java.util.concurrent.CompletableFuture@379619aa[Not completed]] -> Output ...Running r2Running r1
    3. Suppliers [com.venkat.java8.concurrent.Exercise1CompletableFuture$$Lambda$151/212628335@1175e2db, com.venkat.java8.concurrent.Exercise1CompletableFuture$$Lambda$152/1579572132@36aa7bc2] (supplyAsync) -> CFs [java.util.concurrent.CompletableFuture@76ccd017[Completed normally], java.util.concurrent.CompletableFuture@182decdb[Completed normally]] -> Results [s1, s2]
    4. Completed future: {Obj: java.util.concurrent.CompletableFuture@26f0a63f[Completed normally], isDone: true, result: I'm done!}
2. Access methods
    1. Status checkers
        1. isDone - false
        2. numberofDependents: 0
        3. getNow: null
        4. join: Completed for join!
    2. Completion methods
        1. complete("Completing now!"): true, Result= Completing now!
        2. completeExceptionally(new IllegalArgumentException()): true, isCompletedExceptionally= true
        3. cancel(true): true, isCancelled= true

