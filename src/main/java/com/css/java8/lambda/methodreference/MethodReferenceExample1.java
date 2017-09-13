package com.css.java8.lambda.methodreference;

/**
 * @author Kishore Routhu on 12/9/17 6:29 PM.
 */
public class MethodReferenceExample1 {

    public static void main(String[] args) {
        Thread t = new Thread(MethodReferenceExample1::printMessage);
                    //MethodReferenceExample1::printMessage ==  () -> printMessage()  method reference on static method
        t.start();
    }

    private static void printMessage() {
        System.out.println("Hello");
    }

}
