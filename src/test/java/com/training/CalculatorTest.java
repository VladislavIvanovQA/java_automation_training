package com.training;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void sum() {
        Calculator calculator = new Calculator();
        int sum = calculator.sum(1, 3);
        assertEquals(4, sum);
    }

    @Test
    public void val() {
        Calculator calculator = new Calculator();
        int val = calculator.val(3, 1);
        assertEquals(3, val);
    }

    @Test
    public void minus() {
        Calculator calculator = new Calculator();
        int minus = calculator.minus(3, 1);
        assertEquals(2, minus);
    }

    @Test
    public void del() {
        Calculator calculator = new Calculator();
        int del = calculator.del(3, 1);
        assertEquals(3, del);
    }

    @Test(expected = ArithmeticException.class)
    public void valExp(){
        Calculator calculator = new Calculator();
        calculator.val(3, 0);
    }

    @Test(expected = ArithmeticException.class)
    public void delExp(){
        Calculator calculator = new Calculator();
        calculator.del(3, 0);
    }
}