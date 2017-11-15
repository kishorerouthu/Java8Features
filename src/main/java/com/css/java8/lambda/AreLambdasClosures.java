package com.css.java8.lambda;

import java.io.InputStream;
import java.util.function.Function;

import jdk.nashorn.internal.ir.FunctionNode;

/**
 * @author Kishore Routhu on 15/9/17 8:12 AM.
 */
public class AreLambdasClosures {

    public Function<Integer, Integer> make_fun() {

        final int n = 1;

        return arg -> {
            System.out.print(n + " " + arg + ":");
            arg = arg + n;
            return arg;
        };
    }

    public void try_it() {
        Function<Integer, Integer> s = make_fun();
        Function<Integer, Integer> t = make_fun();

        for (int i = 0; i < 5; i++)
            System.out.println(s.apply(i));

        for (int i = 11; i < 15; i++)
            System.out.println(t.apply(i));

    }

    public static void main(String[] args) {
        new AreLambdasClosures().try_it();
    }

}

/*
    It's a mixed bag: we can indeed access n, but we immediately run into trouble when we try to modify n.
    The error message is: local variables referenced from a lambda expression must be final or effectively final.

    It turns out that in Java, lambdas only close over values, but not variables. Java requires those values
    to be unchanging, as if they had been declared final. So they mush be final whether you declare them that way or not.
    Thus, "effectively final". And thus, Java has "closures with restrictions", which might not be "perfect" closures,
    but are nonetheless still quite useful.

    If we create heap-based objects, we can modify the object, because the compiler only cares that the
    reference is not modified
    Example :

    class MyInt {
        int i = 0;
    }

    public class AreLambdasClosures2 {
        public Function<Integer, Integer> make_fun2() {
            MyInt n = new MyInt();
            return arg -> n.i + arg;
        }
    }

 */