package com.bayrktlihn.homework_two;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanNumeralsConverterTest {


    @ParameterizedTest
    @CsvSource({"1,I", "2,II", "3,III", "4,IV", "5,V", "50,L", "88,LXXXVIII", "99,XCIX", "999,CMXCIX", "9999,XCIXCMXCIX","400,CD"})
    void test(int number, String expect) {
        assertEquals(expect, RomanNumeralsConverter.convert(number));

    }

}
