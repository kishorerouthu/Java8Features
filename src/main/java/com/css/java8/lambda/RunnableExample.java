package com.css.java8.lambda;

/**
 * @author Kishore Routhu on 7/9/17 8:54 AM.
 */
public class RunnableExample {

    public static void main(String[] args) {
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("printed inside runnable");
            }
        });

        myThread.start();

        Thread lamdaThread = new Thread(() -> System.out.println("printed inside lamda runnable"));
        lamdaThread.start();
    }
}
