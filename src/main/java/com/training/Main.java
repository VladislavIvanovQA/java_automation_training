package com.training;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int a = calculator.sum(1, 2);
        int b = calculator.minus(2, 1);
        int c = calculator.val(a, b);
        int d = calculator.del(a + b, c);

        System.out.println(a + " " + b + " " + c + " " + d);
    }
}
