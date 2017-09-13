package com.css.java8.lambda;

/**
 * @author Kishore Routhu on 7/9/17 8:44 AM.
 */
public class TypeInferenceExample {

    public static void main(String[] args) {
        StringLengthLambda lambda = s -> s.length();
        System.out.println(lambda.getLength("Hello, World...!"));

        printLambda(s -> s.length());
    }

    public static void printLambda(StringLengthLambda lambda) {
        System.out.println(lambda.getLength("Hello, World...!"));
    }
}

interface StringLengthLambda {
    int getLength(String s);
}
