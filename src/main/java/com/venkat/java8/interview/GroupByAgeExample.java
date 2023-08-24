package com.venkat.java8.interview;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.venkat.java.exercises.common.SampleExerciseBase;
import com.venkat.utils.Pair;

class Person {
	private String name;
	private int age;

    public Person(String nm, int age) {
    	this.name = nm;
    	this.age = age;
    }

    public String getName() {
    	return this.name;
    }

    public int getAge() {
    	return this.age;
    }

    public Pair<Integer> getAgeRange() {
    	int ageMin = (age / 10) * 10;
    	return new Pair<>(ageMin, ageMin + 10);
    }

    public String toString() {
        return String.format("{Name: %s, Age: %s}", name, age);
    }

}

public class GroupByAgeExample extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Group by age solver";

	public GroupByAgeExample() {
		super(EXERCISE_TITLE);
	}

	public Map<Integer, List<Person>> getPersonsByAge(List<Person> persons) {
		return Optional.ofNullable(persons)
                   .map(personsList -> personsList
                                           .stream()
                                           .collect(Collectors.groupingBy(Person::getAge)))
                   .orElse(null);
	}

    public Map<Pair<Integer>, List<Person>> getPersonsByAgeRange(List<Person> persons) {
		return Optional.ofNullable(persons)
                   .map(personsList -> personsList
                                           .stream()
                                           .collect(Collectors.groupingBy(Person::getAgeRange)))
                   .orElse(null);
	}

	@Override
	public void exerciseOutput() {
        List<Person> sampleSpace =
            Arrays.asList(new Person("Venkat", 42),
                          new Person("Abijit", 23),
                          new Person("Veena", 49),
                          new Person("Mani", 42));
        GroupByAgeExample solver = new GroupByAgeExample();
        printfln("Grouped by age: %s", solver.getPersonsByAge(sampleSpace));
        printfln("Grouped by age range: %s", solver.getPersonsByAgeRange(sampleSpace));
	}

}
