package com.css.java8.lambda;

/**
 * @author Kishore Routhu on 6/9/17 2:37 PM.
 */
public class Greeter {

    public void greet(Greeting greeting) {
        greeting.perform();
    }

    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        greeter.greet(() -> System.out.println("Hello, World...!"));

        /*
         *********** Examples *************
             greetingFunction = () -> System.out.println("Hello, World...!");
             doubleFunction = (int a) -> a * 2;
             addFunction = (int a, int b) -> a + b
             safeDivideFunction = (int a, int b) -> (b == 0)? 0 : a/b;
             stringLengthCountFunction = (String s) -> s.length();
         **********************************
         */
    }
}
