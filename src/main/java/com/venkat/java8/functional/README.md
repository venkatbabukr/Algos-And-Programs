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
7. Supplier in Stream.generate - Generate random UUIDs: [912f5f55-4c86-4721-bc57-177056c58738, e5dc6230-e61f-4f3a-a490-158224f78887, 82d98805-f3c2-4f9e-85a1-01119a8cb79d, 1cfde0ee-11ee-413b-8d1e-65862844dea0, 89036026-8496-4b92-a483-996f6f31bbb5, 18f90574-ec5f-4699-8af6-28060697c062, 150aa890-6bfb-4f82-93a9-fa2b63c38bfc, 7c4fdecd-45a9-4310-9bd2-855365db0707, 728c5d82-dcde-4c25-9d85-ed63b831ba91, 9fff6cda-e5fa-4527-aba6-abd2eb75d1f6]
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

