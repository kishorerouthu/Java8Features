package com.css.java8.lambda;

import java.util.function.Consumer;

/**
 * @author Kishore Routhu on 12/9/17 6:13 PM.
 */
public class ThisReferenceExample {

    public void process(int i, Consumer<Integer> consumer) {
        consumer.accept(i);
    }

    public void execute() {
        process(10, i -> {
            System.out.println("Value of i is " + i);
            System.out.println(this);
        });
    }

    @Override
    public String toString() {
        return "This is an instance of " + ThisReferenceExample.class.getSimpleName();
    }

    public static void main(String[] args) {


        ThisReferenceExample thisReferenceExample = new ThisReferenceExample();
        //java7
        thisReferenceExample.process(10, new Consumer<Integer>() {
            @Override
            public void accept(Integer i) {
                System.out.println("Value of i is " + i);
                System.out.println(this);
            }

            @Override
            public String toString() {
                return "This is an instance of process";
            }
        });

        //java8
        thisReferenceExample.process(10, i -> {
            System.out.println("Value of i is " + i);
            //System.out.println(this); //This won't work because in lambda expression this reference refer to actual class not anonymous class
        });

        thisReferenceExample.execute();
    }
}
