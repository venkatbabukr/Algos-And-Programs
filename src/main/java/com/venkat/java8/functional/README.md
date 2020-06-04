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
7. Supplier in Stream.generate - Generate random UUIDs: [5d90aaac-7dcf-44c2-bbdd-cd0bcef378b2, 4234641f-6cde-414d-981a-a78b37d19bce, 04e0e41d-3538-4a08-a569-43084b567374, 037f4b29-6e65-46d1-bab5-3422df2f9ca7, ccd940a9-dd5b-4b18-9d17-03c7be20d5b4, 985b7d70-3c9f-41c1-ae4e-d3cd692f369c, 89b3725e-41f9-4f92-bef0-07dd1b0b4d18, cd561df7-e465-4d67-a202-bb9efcbc70da, ba3fc974-fb13-4096-8490-e455036118ca, e69699e6-5fa2-4527-9b21-e495c1990c5f]
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

