 ------------------------- 
| Java8 Stream Collectors |
 ------------------------- 
1. [Java8 Optional (java.util.Optional)](Exercise1Optional.java)
2. [Java8 StringJoiner (java.util.StringJoiner)](Exercise2StringJoiner.java)

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

