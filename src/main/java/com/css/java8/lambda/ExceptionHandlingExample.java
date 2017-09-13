package com.css.java8.lambda;

import java.util.function.BiConsumer;

/**
 * @author Kishore Routhu on 12/9/17 4:48 PM.
 */
public class ExceptionHandlingExample {

    public static void main(String[] args) {
        int someNumbers[] = {1, 2, 3, 4};
        int key = 2;

        process(someNumbers, key, wrapLambda((v, k) -> System.out.println(v / k)));
    }

    private static void process(int[] someNumbers, int key, BiConsumer<Integer, Integer> consumer) {
        for (int i : someNumbers) {
            consumer.accept(i, key);
        }
    }

    private static <T> BiConsumer<T, T> wrapLambda(BiConsumer<T, T> consumer) {
        return (v, k) -> {
            try {
                consumer.accept(v, k);
            } catch (ArithmeticException ae) {
                System.out.println("Exception caught in wrapper lambda");
            }
        };
    }

}
