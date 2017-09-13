package com.css.java8.lambda;

import java.util.function.Consumer;

/**
 * @author Kishore Routhu on 12/9/17 5:41 PM.
 */
public class ClosureExample {

    public static void main(String[] args) {
        int a = 10;
        int b = 20; // final or effectively final ( closure )

        doProcess(a, i -> System.out.println(i + b));

    }

    private static void doProcess(int i, Consumer<Integer> consumer) {
        consumer.accept(i);
    }
}
