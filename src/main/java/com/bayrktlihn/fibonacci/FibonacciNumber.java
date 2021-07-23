package com.bayrktlihn.fibonacci;

public class FibonacciNumber {
    public int find(int order) {

        if (order <= 0) {
            throw new IllegalArgumentException();
        }

        if (order == 1 || order == 2)
            return 1;

        return find(order - 1) + find(order - 2);

    }
}
