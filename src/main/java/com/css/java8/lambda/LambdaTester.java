package com.css.java8.lambda;

/**
 * @author Kishore Routhu on 13/7/17 8:11 AM.
 */
public class LambdaTester {

    public static void main(String[] args) {
        LambdaTester tester = new LambdaTester();

        //with type declaration
        MathOperation addition = (int a, int b) -> a + b;

        //with out type declaration
        MathOperation subtraction = (a, b) -> a - b;

        //with return statement along with curley braces
        MathOperation multiplication = (a, b) -> { return a * b; };

        //without return statement and without curley braces
        MathOperation division = (int a, int b) -> a/b;

        //without parenthesis
        GreetingService greetingService = message -> System.out.println("Hello " + message);

        //with parenthesis
        GreetingService greetingService1 = (message) -> System.out.println("Hello " + message);

        greetingService.sayMessage("Ramesh");
        greetingService.sayMessage("Suresh");

        System.out.println(tester.operate(3, 5, addition));
        System.out.println(tester.operate(5,3, subtraction));
        System.out.println(tester.operate(5,3, multiplication));
        System.out.println(tester.operate(10,2, division));


    }

    /* functional interface for performing maths operations */
    interface MathOperation {
        int operation(int a, int b);
    }

    /* functional interface for greetings*/
    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}
