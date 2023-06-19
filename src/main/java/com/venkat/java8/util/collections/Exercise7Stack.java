package com.venkat.java8.util.collections;

import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import java.util.Stack;

import com.venkat.java.exercises.common.SampleExerciseBase;

public class Exercise7Stack extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Java8 Stack (java.util.Stack)";

	protected Exercise7Stack() {
		super(EXERCISE_TITLE);
	}

	@Override
	public void exerciseOutput() {
		Stack<Integer> testStack = new Stack<>();

        printfln("1. Empty created stack: %s (%s extends %s)", testStack, Stack.class, Stack.class.getSuperclass());

        // Push operations
        testStack.push(10);
        testStack.push(5);
        testStack.push(1);
        
        printfln("2. Stack after 3 pushes: %s", testStack);
        printfln("3. empty()=%s, firstElement()=%d, lastElement()=%d", testStack.empty(), testStack.firstElement(), testStack.lastElement());
        printfln("4. Stack: %s, peek()=%d", testStack, testStack.peek());
        printfln("5. pop()=%d, Stack after pop: %s", testStack.pop(), testStack);
        int searchExistingNum = 10, searchMissingNum = 4;
        printfln("6. Stack: %s, search(%d)=%d, search(%d)=%d", testStack, searchExistingNum, testStack.search(searchExistingNum), searchMissingNum, testStack.search(searchMissingNum));
        Collections.reverse(testStack);
        printfln("7. Stack after reverse: %s", testStack);
        printfln("8. pop()=%d, Stack after pop: %s", testStack.pop(), testStack);
	}
	
    public static void main(String[] args) {
        (new Exercise7Stack()).executeExercise();
    }

}
