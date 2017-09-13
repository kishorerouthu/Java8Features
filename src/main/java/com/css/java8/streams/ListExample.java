package com.css.java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kishore Routhu on 8/9/17 3:38 PM.
 */
public class ListExample {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> evens = numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        List<Integer> odds = numbers.stream().filter(n -> n % 2 != 0).collect(Collectors.toList());
        System.out.println("NUMBERS :: " + numbers);
        System.out.println("EVENS :: " + evens);
        System.out.println("ODDS :: " + odds);

        System.out.println(isMatchesGroup("services/dataservice"));

    }

    private static boolean isMatchesGroup(String filePath) {
        List<String> basePatterns = getBasePatterns();
        return basePatterns.stream().filter(pattern -> filePath.startsWith(pattern)).findAny().isPresent();
    }

    private static List<String> getBasePatterns() {
        return Arrays.asList("src/main/webapp/pages/", "sr/main/webapp/WEB-INF/prefabs/", "services");
    }
}
