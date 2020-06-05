 ------------------------------ 
| Java8 Functional Programming |
 ------------------------------ 
1. [Function Reference Types](Exercise1FunctionReferenceTypes.java)
2. [OOTB Functional Interfaces (java.util.function)](Exercise2OOTBFunctionalInterfaces.java)
3. [Functional Interfaces (java.util.function) additional support](Exercise3JavaFunctionalInterfacesExtraSupport.java)

Function Reference Types
------------------------
1. Fliter Meals ok for Venkat using venkatFilter::ok - Object method reference: [Tuna Fish[NONVEG/DINNER], Doritos[CONTINENTAL/SNACK], Steamed Rice[ASIANVEG/LUNCH], Idly Sambar[ASIANVEG/BREAKFAST]]
2. Print using this::printf - Object method reference: 12, 39, 2, 1, 8, 22, 84, 94, 1, 4, 2, 10, 
3. Filter using Meal::isVegMeal - Type method reference: [Steamed Rice[ASIANVEG/LUNCH], Idly Sambar[ASIANVEG/BREAKFAST]]
4. Map to Hex using Integer::toHexString - Static method reference: [c, 27, 2, 1, 8, 16, 54, 5e, 1, 4, 2, a]
5. Add numbers to TreeSet using toCollection(TreeSet::new) - Constructor reference: [1, 2, 4, 8, 10, 12, 22, 39, 84, 94]

OOTB Functional Interfaces (java.util.function)
-----------------------------------------------
1. UnaryOperator in Stream.iterate() - Generate stream of odd numbers: [1, 3, 5, 7, 9, 11, 13, 15, 17, 19]
2. BinaryOperator in all reduce functions - Sum of array: Optional[279]
3. Predicate in Stream.filter - Filter even numbers in array: [12, 2, 8, 22, 84, 94, 4, 2, 10]
4. BiPredicate - Random example - isSubStr.test(str1, str2): false, true
5. Consumer - Inside Stream.forEach(): [12], [39], [2], [1], [8], [22], [84], [94], [1], [4], [2], [10], 
6. BiConsumer - Set::add...: [str1, str2]
7. Supplier in Stream.generate - Generate random UUIDs: [704ea975-97b2-4dc5-884f-e0fc615cde3d, a1efdf73-4740-4b18-a2e3-d2f1fcb102c5, efee9e95-737e-4866-8a70-53d715a8d33d, f8c1690c-376e-4dd2-bbfb-d0733340a885, 6e7dba6e-2a0b-4b49-95d5-8bd9fc5867bb, b0f65ee7-7585-4a51-8d34-7acc6363e6fc, 0b7e0ca7-08f2-41c7-8e03-b3ebd7430a5e, 53c0869a-de57-4072-bd5c-c90dea89bd4f, 50e44d0d-31d0-4624-9ce4-d03ac1272d9a, c5f88da5-d32e-4439-a99c-01c242c10573]
8. Function - Random Example - Rounding a double number 1.234: 1
9. BiFunction - Random Example - 2.0 ^ 3.0: 8.0

Functional Interfaces (java.util.function) additional support
-------------------------------------------------------------
1. BinaryOperator.maxBy in Stream.reduce() to find max in array: Optional[94]
2. Predicate.and in Stream.filter() to find even numbers less than 20: [12, 2, 8, 4, 2, 10]
3. Predicate.negate in Stream.filter() to find numbers greater than 20: [39, 22, 84, 94]
4. BiPredicate too has .and & .negate...
5. Consumer.andThen - Print number & it's negative - Inside Stream.forEach(): [12:-12], [39:-39], [2:-2], [1:-1], [8:-8], [22:-22], [84:-84], [94:-94], [1:-1], [4:-4], [2:-2], [10:-10], 
6. Function.andThen - Rounding a double number 1.234 andThen formatting 1.234: $1
7. Function.compose - Rounding a double number 1.234 andThen formatting - but using compose 1.234: $1

