package com.bayrktlihn.homework_two;

import java.util.ArrayList;
import java.util.List;

public class RomanNumeralsConverter {
    public static String convert(int number) {

        StringBuilder sb = new StringBuilder();


        List<Integer> divides = new ArrayList<>();
        divides.add(1000);
        divides.add(500);
        divides.add(100);
        divides.add(50);
        divides.add(10);
        divides.add(5);
        divides.add(1);

        List<String> romanValues = new ArrayList<>();
        romanValues.add("M");
        romanValues.add("D");
        romanValues.add("C");
        romanValues.add("L");
        romanValues.add("X");
        romanValues.add("V");
        romanValues.add("I");

        for (int i = 0; i < divides.size(); i++) {
            if (number < 1) {
                break;
            }

            final Integer currentDivide = divides.get(i);
            if (number % currentDivide == 0) {
                sb.append(concat(romanValues.get(i), number / currentDivide));
                number %= currentDivide;
            } else if (i + 2 != divides.size()) {
                int nextDivide = divides.get(i + 1);
                if (nextDivide + currentDivide == number) {
                    sb.append(romanValues.get(i + 1) + romanValues.get(i));
                    number -= (currentDivide - nextDivide);
                }
            }


        }
        return sb.toString();
    }


    private static String concat(String letter, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(letter);
        }

        return sb.toString();
    }


}
