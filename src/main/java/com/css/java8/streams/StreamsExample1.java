package com.css.java8.streams;

import java.util.Arrays;
import java.util.List;

import com.css.java8.lambda.functioninterface.Person;

/**
 * @author Kishore Routhu on 14/9/17 9:04 AM.
 */
public class StreamsExample1 {

    public static void main(String[] args) {

        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Mattew", "Arnold", 39)
        );

        people.stream().filter(p -> p.getLastName().startsWith("C"))
                .forEach(System.out::println);

        long sumOfAges = people.stream().filter(p -> p.getFirstName().startsWith("C"))
                .mapToLong(p -> p.getAge()).sum();
        System.out.println(sumOfAges);

        long c = people.stream().filter(p -> p.getFirstName().startsWith("M"))
                 .count();
        System.out.println(c);
    }
}
