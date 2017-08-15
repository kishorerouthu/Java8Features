package com.css.java8.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @author Kishore Routhu on 15/8/17 11:32 AM.
 */
public class SemaphoreDeadLock {

    public static void main(String[] args) {
        Semaphore first = new Semaphore(1);
        Semaphore second = new Semaphore(1);

        Thread t1 = new Thread(new DoubleResourceGrabber(first, second));
        Thread t2 = new Thread(new DoubleResourceGrabber(second, first));

        t1.start();
        t2.start();
    }

    private static class DoubleResourceGrabber implements Runnable {

        //Semaphore for the first resource
        private final Semaphore first;

        //Semaphore for the second resource
        private final Semaphore second;

        public DoubleResourceGrabber(Semaphore first, Semaphore second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public void run() {
            try {
                Thread t = Thread.currentThread();

                first.acquire();
                System.out.println(t + " acquired " + first);

                Thread.sleep(200); //demonstrate deadlock

                second.acquire();
                System.out.println(t + " acquired " + second);

                second.release();
                System.out.println(t + " released " + second);

                first.release();
                System.out.println(t + " released " + first);

            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
