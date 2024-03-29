package com.venkat.java8.concurrent;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

import com.venkat.java.exercises.common.SampleExerciseBase;

public class Exercise1CompletableFuture extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Completable Future";

    protected Exercise1CompletableFuture() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        // 1. Different methods of creation:

        printfln("1. Creating completable futures");
        // 1.1 Using new
        CompletableFuture<String> f1 = new CompletableFuture<>();
        printfln("    1. Using new: {Obj: %s, isDome: %s}", f1, f1.isDone());
        
        // 1.2 Using runAsync
        List<Runnable> runnables = Arrays.asList(() -> print("Running r1"), () -> print("Running r2"));
        List<CompletableFuture<Void>> runnableFutures = runnables
                                                            .stream()
                                                            .map(CompletableFuture::runAsync)
                                                            .collect(toList());
        printf("    2. Runnables %s (runAsync) -> CFs %s -> Output ...", runnables, runnableFutures);
        println();

        // 1.3. Using supplyAsync
        List<Supplier<String>> suppliers = Arrays.asList(() -> "s1", () -> "s2");
        List<CompletableFuture<String>> supplierFutures = suppliers
                                                              .stream()
                                                              .map(CompletableFuture::supplyAsync)
                                                              .collect(toList());
        List<String> results = suppliers
                                   .stream()
                                   .map(CompletableFuture::supplyAsync)
                                   .map(CompletableFuture::join)
                                   .collect(toList());
        printfln("    3. Suppliers %s (supplyAsync) -> CFs %s -> Results %s", suppliers, supplierFutures, results);

        // 1.4 Completed future
        CompletableFuture<String> f2 = CompletableFuture.completedFuture("I'm done!");
        try {
            printfln("    4. Completed future: {Obj: %s, isDone: %s, result: %s}", f2, f2.isDone(), f2.get());
        } catch (InterruptedException | ExecutionException e) {
            printfln("Got exception while creating completed future: " + e.getMessage());
            e.printStackTrace(getPrintStream());
            Thread.currentThread().interrupt();
        }

        // 2. Access methods
        printfln("2. Access methods");

        // 2.1 Status checkers
        CompletableFuture<String> f3 = new CompletableFuture<>();
        printfln("    1. Status checkers");
        printfln("        1. isDone - %s", f3.isDone());
        printfln("        2. numberofDependents: %s", f3.getNumberOfDependents());
        printfln("        3. getNow: %s", f3.getNow(null));
        f3.complete("Completed for join!");
        printfln("        4. join: %s", f3.join());

        // 2.2 Status modifiers
        printfln("    2. Status modifiers");
        CompletableFuture<String> f4 = new CompletableFuture<>();
        printfln("        1. complete(\"Completing now!\"): %s, Result= %s", f4.complete("Completing now!"), f4.join());

        CompletableFuture<String> f5 = new CompletableFuture<>();
        printfln("        2. completeExceptionally(new IllegalArgumentException()): %s, isCompletedExceptionally= %s",
                 f5.completeExceptionally(new IllegalArgumentException()),
                 f5.isCompletedExceptionally());

        CompletableFuture<String> f6 = new CompletableFuture<>();
        printfln("        3. cancel(true): %s, isCancelled= %s",
                 f6.cancel(true),
                 f6.isCancelled());

        // 3. Processing results - then methods
        printfln("3. Processing results - then & when methods");
        print("    1. thenRun(): ");
        CompletableFuture.runAsync(() -> print("Running inside the future... "))
                         .thenRun(() -> println("Running inside thenRun()!"));
        print("    2. thenAccept(): ");
        CompletableFuture.supplyAsync(() -> "Result from inside completable...")
                         .thenAccept(result -> printfln("Got result - %s", result));
        printfln("    3. thenApply(): %s",
                 CompletableFuture.supplyAsync(() -> "Result from inside completable...")
                                  .thenApply(result -> result + " suffix from thenApply!").join());
        print("    4. whenComplete() - Just like accept: ");
        CompletableFuture.supplyAsync(() -> "Result from inside completable...")
                         .whenComplete((result, ex) -> {
                             if (ex != null) {
                                 printfln("Got exception on completion - %s", ex.getMessage());
                             } else {
                                 printfln("Got result on completion - %s", result);
                             }
                         });
        printfln("    5. handle() - that can return result: %s",
                 CompletableFuture.supplyAsync(() -> "Result from inside completable...")
                                  .handle((result, ex) -> String.join("with join of", ex != null ? ex.getMessage() : result, " handler suffix..."))
                                  .join());

        // 4. Composing two or more CompletableFutures
        printfln("4. Composing two or more CompletableFutures");
        print("    1. runAfterEither(): ");
        CompletableFuture.runAsync(() -> print("Inside CF1... "))
                         .runAfterEither(CompletableFuture.runAsync(() -> print("Inside CF2...")), () -> println("Inside Runnable after either CF1 or CF2"));

        printfln("    2. thenCompose(): %s",
                 CompletableFuture.supplyAsync(() -> "Result from CF1")
                                  .thenCompose(result -> CompletableFuture.supplyAsync(() -> result + " + Result from CF2")).join());
        print("    3. allOf(): ");
        CompletableFuture.allOf(CompletableFuture.runAsync(() -> print(" CF1 ")),
                                CompletableFuture.runAsync(() -> print(" CF2 ")))
                         .thenRun(() -> println());
        print("    4. anyOf(): ");
        CompletableFuture.anyOf(CompletableFuture.runAsync(() -> print(" CF1 ")),
                                CompletableFuture.runAsync(() -> print(" CF2 ")))
                         .thenRun(() -> println());
    }
    
    public static void main(String[] args) {
        (new Exercise1CompletableFuture()).executeExercise();
    }

}
