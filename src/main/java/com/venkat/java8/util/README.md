 ------------------------- 
| Java8 Stream Collectors |
 ------------------------- 
1. [Java8 Optional (java.util.Optional)](Exercise1Optional.java)
2. [Java8 StringJoiner (java.util.StringJoiner)](Exercise2StringJoiner.java)
3. [Java8 Base64 (java.util.Base64)](Exercise3Base64EncodeDecode.java)
4. [Java8 Comparator Enhancements (java.util.Comparator)](Exercise4ComparatorEnhancements.java)

Java8 Optional (java.util.Optional)
-----------------------------------
1. Create using Optional.of <Test str>: Optional[Test str]
2. Create using Optional.ofNullable: Optional.empty
3. Optional.isPresent() of <Test str>: true
4. Optional.get() of <Test str>: Test str
5. Optional.orElse: Other Non null str!
6. Optional.ifPresent() of <Test str>: Consuming... Test str
7. Optional.map() to all upper case of <Test str>: Optional[TEST STR]
8. OptionalInt of <1>: OptionalInt[1]
9. OptionalLong of <1>: OptionalLong[1]
10. OptionalDouble of <1>: OptionalDouble[1.0]

Java8 StringJoiner (java.util.StringJoiner)
-------------------------------------------
1. Simple join operation using delim ~: First~Second~Third
2. Simple join operation using prefix <joiner1>, suffix </joiner1>, delim ~: <joiner1>First~Second~Third</joiner1>
2. Length with prefix <joiner1>, suffix </joiner1>, delim ~: <joiner1>First~Second~Third</joiner1>[37]
4. Concat using merge(): <joiner2>1^2^3^First~Second~Third</joiner2>[43]

Java8 Base64 (java.util.Base64)
-------------------------------
1. Encoding text <Hello world???> to bytes: [83, 71, 86, 115, 98, 71, 56, 103, 100, 50, 57, 121, 98, 71, 81, 47, 80, 122, 56, 61]
2. Encoding text <Hello world???> to base64 string: SGVsbG8gd29ybGQ/Pz8=
3. Encoding text <Hello world???> to base64 string without padding: SGVsbG8gd29ybGQ/Pz8
4. URL Encoding text <Hello world???>: SGVsbG8gd29ybGQ_Pz8=
5. MIME Encoding text <Hello world???> repeated 5 times: SGVsbG8gd29ybGQ/Pz8NCkhlbGxvIHdvcmxkPz8/DQpIZWxsbyB3b3JsZD8/Pw0KSGVsbG8gd29y
bGQ/Pz8NCkhlbGxvIHdvcmxkPz8/

Java8 Comparator Enhancements (java.util.Comparator)
----------------------------------------------------
1. Anonymous comparator example (x, y) -> y - x: [94, 84, 39, 22, 12, 10, 8, 4, 2, 2, 1, 1]
2. Comparator.reverseOrder(): [94, 84, 39, 22, 12, 10, 8, 4, 2, 2, 1, 1]
3. Comparator.reversed(): [Tuna Fish[NONVEG/DINNER], Doritos[CONTINENTAL/SNACK], Steamed Rice[ASIANVEG/LUNCH], Toast/Sandwitch[CONTINENTAL/BREAKFAST], Idly Sambar[ASIANVEG/BREAKFAST]]
4. Comparator.comparing() sort meals on time: [Toast/Sandwitch[CONTINENTAL/BREAKFAST], Idly Sambar[ASIANVEG/BREAKFAST], Steamed Rice[ASIANVEG/LUNCH], Doritos[CONTINENTAL/SNACK], Tuna Fish[NONVEG/DINNER]]
5.Comparator.comparing() sort meals on time reverse order: [Tuna Fish[NONVEG/DINNER], Doritos[CONTINENTAL/SNACK], Steamed Rice[ASIANVEG/LUNCH], Toast/Sandwitch[CONTINENTAL/BREAKFAST], Idly Sambar[ASIANVEG/BREAKFAST]]
6. Comparator.thenComparing sort meals on time and name: [Idly Sambar[ASIANVEG/BREAKFAST], Toast/Sandwitch[CONTINENTAL/BREAKFAST], Steamed Rice[ASIANVEG/LUNCH], Doritos[CONTINENTAL/SNACK], Tuna Fish[NONVEG/DINNER]]
7. Comparator.nullsFirst() sort meals on time: [null, null, null, Idly Sambar[ASIANVEG/BREAKFAST], Toast/Sandwitch[CONTINENTAL/BREAKFAST], Steamed Rice[ASIANVEG/LUNCH], Doritos[CONTINENTAL/SNACK], Tuna Fish[NONVEG/DINNER]]

