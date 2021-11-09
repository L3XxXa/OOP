package ru.nsu.malov;


import ru.nsu.malov.operations.Operation;

import java.io.*;
import java.util.*;

public class Calculator {
    private final Map<String, Operation> operation = new HashMap<>();
    private final String input = "input.txt";
    private final String output = "output.txt";

    private void initMapOperation() {
        CalculatorFabric calculatorFabric = new CalculatorFabric();
        ArrayList<Operation> operations = calculatorFabric.operationFabric();
        operation.put("+", operations.get(0));
        operation.put("cos", operations.get(1));
        operation.put("/", operations.get(2));
        operation.put("ln", operations.get(3));
        operation.put("log", operations.get(4));
        operation.put("*", operations.get(5));
        operation.put("^", operations.get(6));
        operation.put("sin", operations.get(7));
        operation.put("sqrt", operations.get(8));
        operation.put("-", operations.get(9));
    }

    private boolean isNum(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    private int operator(String operator) {
        if (operator.equals("-") || operator.equals("+") ||
                operator.equals("*") || operator.equals("/") ||
                operator.equals("log") || operator.equals("^")) {
            return 2;
        } else if (operator.equals("cos") || operator.equals("sin") ||
                operator.equals("ln") || operator.equals("sqrt")) {
            return 1;
        } else {
            throw new IllegalArgumentException("Not an operator");
        }
    }

    /**
     * Method to parse str and init calculating the answer
     *
     * @param str - string to calculate
     * @return result
     */

    public double calculate(String str) {
        initMapOperation();
        String[] input = str.split(" ");
        Stack<Double> stack = new Stack<>();
        ArrayList<Double> operands = new ArrayList<>();
        for (int i = input.length - 1; i >= 0; i--) {
            if (isNum(input[i])) {
                stack.push(Double.parseDouble(input[i]));
            } else if (operator(input[i]) == 2) {
                operands.add(stack.pop());
                operands.add(stack.pop());
                stack.push(operation.get(input[i]).init(operands));
            } else {
                operands.add(stack.pop());
                stack.push(operation.get(input[i]).init(operands));
            }
        }
        return stack.pop();
    }

    /**
     * Method to open file, calculate string and execute method to write the answer to the file
     */
    public void calculateString() throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(input));
            String line = bufferedReader.readLine();
            double res = calculate(line);
            String resStr = Double.toString(res);
            writeAnswerToFile(resStr);
            bufferedReader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    private void writeAnswerToFile(String res) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(output);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(res);
            printWriter.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}

