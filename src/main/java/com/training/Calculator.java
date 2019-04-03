package com.training;

public class Calculator extends Methods {
    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public int val(int a, int b) {
        if (a == 0 || b == 0) throw new ArithmeticException("Сannot be multiplied to zero");
        return a * b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }

    @Override
    public int del(int a, int b) {
        if (a == 0 || b == 0) throw new ArithmeticException("Сannot be divided by zero");
        return a / b;
    }
}
