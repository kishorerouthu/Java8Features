package com.css.java8.lambda.functioninterface;

/**
 * @author Kishore Routhu on 12/9/17 3:34 PM.
 */
@FunctionalInterface
public interface Condition {
    boolean test(Person person);
}
