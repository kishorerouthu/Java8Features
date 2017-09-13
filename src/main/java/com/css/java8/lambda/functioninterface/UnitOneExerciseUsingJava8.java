package com.css.java8.lambda.functioninterface;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author Kishore Routhu on 12/9/17 12:15 PM.
 */
public class UnitOneExerciseUsingJava8 {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Mattew", "Arnold", 39)
        );

        //Step1 : Sort the list by last name
        Collections.sort(people, (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));

        System.out.println("*************** SORTED LIST ***************");
        //Step2 : Create a method that prints all the elements in the list
        printByCondition(people, p -> true, p -> System.out.println(p));

        System.out.println("************* BY LASTNAME **************");
        //Step3 : Create a method that prints all people that have last name beginning with c
        printByCondition(people, p -> p.getLastName().startsWith("C"), p -> System.out.println(p.getFirstName()));
        //people.stream().filter(person -> person.getLastName().startsWith("C")).forEach(System.out::println);
    }

    private static void printByCondition(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
        //people.stream().filter(p -> predicate.test(p)).forEach(person -> consumer.accept(person));

        for (Person person : people)
            if (predicate.test(person))
                consumer.accept(person);
    }
}
