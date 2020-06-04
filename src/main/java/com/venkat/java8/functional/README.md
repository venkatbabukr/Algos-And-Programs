 ------------------------------ 
| Java8 Functional Programming |
 ------------------------------ 
1. [Function Reference Types](Exercise1FunctionReferenceTypes.java)
2. [OOTB Functional Interfaces (java.util.function)](Exercise2OOTBFunctionalInterfaces.java)
3. [Functional Interfaces (java.util.function) additional support](Exercise3JavaFunctionalInterfacesExtraSupport.java)

Function Reference Types
------------------------
1. Fliter Meals ok for Venkat using venkatFilter::ok - Object method reference: [Tuna Fish[NONVEG/DINNER], Doritos[CONTINENTAL/SNACK], Steamed Rice[ASIANVEG/LUNCH], Idly Sambar[ASIANVEG/BREAKFAST]]
2. Print using System.out::print - Object method reference: 
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
7. Supplier in Stream.generate - Generate random UUIDs: [4127fe4c-662a-47fd-9f54-37fe64f816d6, 6eb49d06-69b1-423a-b248-71b6bc457636, 790e4dfd-895e-40ff-9c27-f34aea577110, 67162738-4f3a-4a1f-9e46-752ccffc570f, 904a995f-80d2-4d2a-a80d-131383a7756f, 167fb6c4-5bca-479e-aa9f-79d6f982a793, 1f556c2f-529b-489e-b315-ee2f50405a94, 1940d52c-8c1b-4d91-82bd-7c4527beac5a, a6d3a805-3ba1-4105-b2a6-2b9057c248a7, 33d401fe-ae2e-4d96-9629-fdb4f696ad8c]
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

