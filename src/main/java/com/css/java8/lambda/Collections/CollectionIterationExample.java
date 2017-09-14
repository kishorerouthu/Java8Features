package com.css.java8.lambda.Collections;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.css.java8.lambda.functioninterface.Person;

/**
 * @author Kishore Routhu on 14/9/17 8:22 AM.
 */
public class CollectionIterationExample {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Mattew", "Arnold", 39)
        );

       System.out.println("Using Extrernal Loops");
        System.out.println();
        System.out.println("Using for loop");
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i));
        }

        System.out.println("Using for in loop");
        for (Person person : people) {
            System.out.println(person);
        }

        System.out.println();
        System.out.println("Using Internal Loop");
        System.out.println("Using for each in java8 ");
        people.forEach(System.out::println);
        people.parallelStream();
    }


}
