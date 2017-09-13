package com.css.java8.lambda.functioninterface;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Kishore Routhu on 12/9/17 12:15 PM.
 */
public class UnitOneExerciseUsingJava7 {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Mattew", "Arnold", 39)
        );

        //Step1 : Sort the list by last name
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });

        System.out.println("*************** SORTED LIST ***************");
        //Step2 : Create a method that prints all the elements in the list
        printList(people);


        System.out.println("************* BY LASTNAME **************");
        //Step3 : Create a method that prints all people that have last name beginning with c
        printByCondition(people, new Condition() {
            @Override
            public boolean test(Person person) {
                return person.getLastName().startsWith("C");
            }
        });
    }

    private static void printList(List<Person> people) {
        for (Person person : people) {
            System.out.println(person);
        }
    }

    private static void printByCondition(List<Person> people, Condition condition) {
        for (Person person : people) {
            if (condition.test(person)) {
                System.out.println(person);
            }
        }
    }
}

