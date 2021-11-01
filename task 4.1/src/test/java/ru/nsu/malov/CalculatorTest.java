package ru.nsu.malov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    public void initCalc() {
        calculator = new Calculator();
    }

    @Test
    public void calculate_addition() {
        Assertions.assertEquals(calculator.calculate("4 + 1"), 5.0);
    }

    @Test
    public void calculate_subtraction() {
        Assertions.assertEquals(calculator.calculate("3 - 2"), 1.0);
    }

    @Test
    public void calculate_multiplication() {
        Assertions.assertEquals(calculator.calculate("4 * 2"), 8.0);
    }

    @Test
    public void calculate_division() {
        Assertions.assertEquals(calculator.calculate("4 / 2"), 2.0);
    }


    @Test
    public void calculate_pow() {
        Assertions.assertEquals(calculator.calculate("4 ^ 2"), 16.0);
    }

    @Test
    public void calculate_sqrt_positiveNumber() {
        Assertions.assertEquals(calculator.calculate("4 sqrt"), 2);
    }

    @Test
    public void calculate_log() {
        Assertions.assertEquals(calculator.calculate("4 log 2"), 2);
    }

    @Test
    public void calculate_ln() {
        Assertions.assertEquals(calculator.calculate("4 ln"), Math.log(4));
    }

    @Test
    public void calculate_sin() {
        Assertions.assertEquals(calculator.calculate("121 sin"), Math.sin(121));
    }

    @Test
    public void calculate_cos() {
        Assertions.assertEquals(calculator.calculate("121 cos"), Math.cos(121));
    }

    @Test
    public void calculate_unknownOperand() {
        String str = "4 $ 1";
        Assertions.assertThrows(IllegalStateException.class, () -> {
            calculator.calculate(str);
        });
    }
}