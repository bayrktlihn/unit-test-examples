package com.bayrktlihn;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DemoTest {


    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before all");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After all");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("After each");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Before each");
    }


    @Test
    public void test1() {
        System.out.println("Test 1");
    }

    @Test
    public void test2() {
        System.out.println("Test 2");
    }

    @Test
    @Disabled
    public void test3() {
        System.out.println("Test 3");
    }

    @Test
    @DisplayName("This display name example")
    public void test4() {
        System.out.println();
    }

}
