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
7. Supplier in Stream.generate - Generate random UUIDs: [6819eb25-0880-4926-b594-2aeaa6a9f535, 5da105f9-ae75-45fd-b350-3d13e4e0d812, e48114a8-043a-48a4-9c74-6268a8b52bf1, 2d26fc0a-244f-4317-97ab-bcfd19a2d39b, e213958e-0863-4818-81d3-d70991e49957, 6583e02d-31b0-4630-804d-6ae57611fbbb, e66dfcf0-d99f-41b9-a7b7-fc977a69212f, 61d95c29-4989-454e-9fcf-d7dee9e1d97e, 71fbc2e7-750b-4e5c-837d-655481eaa4a5, b4b7f143-8cdf-4069-af73-30a6c632e1bc]
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

