package com.css.java8.lambda.methodreference;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.css.java8.lambda.functioninterface.Person;

/**
 * @author Kishore Routhu on 12/9/17 6:29 PM.
 */
public class MethodReferenceExample2 {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Mattew", "Arnold", 39)
        );

        printByCondition(people, p -> true, System.out::println); //Method references

        Printer<Person> printer = new Printer<>();
        printByCondition(people, p -> true, printer::println); //Method references on instance of class Printer
    }

    private static void printByCondition(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
        for (Person person : people)
            if (predicate.test(person))
                consumer.accept(person);
    }

}

class Printer<T> {
    public void println(T t) {
        System.out.println(t.toString());
    }
}