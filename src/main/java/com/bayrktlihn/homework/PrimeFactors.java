package com.bayrktlihn.homework;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactors {
    public static List<Integer> generate(int number) {
        List<Integer> result = new ArrayList<>();

        int divide = 2;
        while (number > 1) {
            if (number % divide == 0) {
                number /= divide;
                result.add(divide);
                divide--;
            }

            divide++;
        }

        return result;
    }
}
