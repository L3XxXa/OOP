package ru.nsu.malov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

class CalculatorTest {
    Stack<Double> stack;
    Calculator calculator;

    @BeforeEach
    private void initCalc() {
        stack = new Stack<>();
        calculator = new Calculator();
    }

    @Test
    public void addition() {
        Assertions.assertEquals(10 + 12, calculator.calculate("+ 10 12"));
    }

    @Test
    public void cos() {
        Assertions.assertEquals(Math.cos(1), calculator.calculate("cos 1"), 0.00001);
    }

    @Test
    public void division() {
        Assertions.assertEquals(12 / 4, calculator.calculate("/ 12 4"));
    }

    @Test
    public void logarithm() {
        Assertions.assertEquals(Math.log(3), calculator.calculate("ln 3"), 0.00001);
    }

    @Test
    public void logarithmWithBase() {
        double res = Math.log(12) / Math.log(4);
        Assertions.assertEquals(res, calculator.calculate("log 12 4"), 0.00001);
    }

    @Test
    public void multiplication() {
        Assertions.assertEquals(4 * 5, calculator.calculate("* 4 5"));
    }

    @Test
    public void pow() {
        Assertions.assertEquals(Math.pow(3, 2), calculator.calculate("^ 3 2"));
    }

    @Test
    public void sin() {
        Assertions.assertEquals(Math.sin(1), calculator.calculate("sin 1"));
    }

    @Test
    public void sqrt() {
        Assertions.assertEquals(Math.sqrt(4), calculator.calculate("sqrt 4"));
    }

    @Test
    public void subtraction() {
        Assertions.assertEquals(5 - 1, calculator.calculate("- 5 1"));
    }

    @Test
    public void notAnOperator() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.calculate("$ 5"));
    }

    @Test
    public void bigLineTest() {
        double res = (5 + 1 - 4) * 2;
        Assertions.assertEquals(res, calculator.calculate("- + * 5 1 4 2"));
    }

}
