package ru.nsu.malov;

import ru.nsu.malov.operations.Operation;

import java.util.*;

public class Calculator {
    private boolean isNum(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    /**
     * Method to parse str and init calculating the answer
     *
     * @param str - string to calculate
     * @return result
     */

    public double calculate(String str) {
        CalculatorFabric calculatorFabric = new CalculatorFabric();
        calculatorFabric.initMapOperation();
        String[] input = str.split(" ");
        Stack<Double> stack = new Stack<>();
        ArrayList<Double> operands = new ArrayList<>();
        for (int i = input.length - 1; i >= 0; i--) {
            if (isNum(input[i])) {
                stack.push(Double.parseDouble(input[i]));
            } else if (calculatorFabric.checkOperator(input[i])) {
                if (calculatorFabric.getArity(input[i]) == 2) {
                    operands.add(stack.pop());
                    operands.add(stack.pop());
                    stack.push(calculatorFabric.calculationFabric(input[i], operands));
                } else if (calculatorFabric.getArity(input[i]) == 1) {
                    operands.add(stack.pop());
                    stack.push(calculatorFabric.calculationFabric(input[i], operands));
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
        return stack.pop();
    }
}

