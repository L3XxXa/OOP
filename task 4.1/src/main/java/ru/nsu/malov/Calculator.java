package ru.nsu.malov;

import java.util.Scanner;

public class Calculator extends Operations {
    public double calculate(String str) {
        Scanner scanner = new Scanner(str);
        Operations operations = new Operations();
        double a = 0;
        double b = 0;
        double res = 0;
        a = Double.parseDouble(scanner.next());
        String operand = scanner.next();
        if (operand.equals("+") ||
                operand.equals("-") ||
                operand.equals("*") ||
                operand.equals("/") ||
                operand.equals("^") ||
                operand.equals("log")) {
            b = Double.parseDouble(scanner.next());
        }
        switch (operand) {
            case "+" -> res = operations.addition(a, b);
            case "*" -> res = operations.multiplication(a, b);
            case "-" -> res = operations.subtraction(a, b);
            case "/" -> res = operations.division(a, b);
            case "^" -> res = operations.pow(a, b);
            case "log" -> res = operations.logWithBase(a, b);
            case "sqrt" -> res = operations.sqrt(a);
            case "sin" -> res = operations.sin(a);
            case "cos" -> res = operations.cos(a);
            case "ln" -> res = operations.ln(a);
            default -> throw new IllegalStateException("Unexpected value: " + operand);
        }
        return res;
    }
}
