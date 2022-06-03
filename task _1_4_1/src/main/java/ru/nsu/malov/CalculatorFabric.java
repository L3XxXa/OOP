package ru.nsu.malov;

import ru.nsu.malov.operations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CalculatorFabric {
    private final Map<String, Operation> operations = new HashMap<>();

    /**
     * Method to make an array list with all operations
     */
    public void initMapOperation() {
        operations.put("+", new Add());
        operations.put("cos", new Cos());
        operations.put("/", new Division());
        operations.put("ln", new Logarithm());
        operations.put("log", new LogarithmWithBase());
        operations.put("*", new Multiplication());
        operations.put("^", new Pow());
        operations.put("sin", new Sin());
        operations.put("sqrt", new Sqrt());
        operations.put("-", new Subtraction());
    }
    public boolean checkOperator(String symbol){
        return operations.containsKey(symbol);
    }

    public double calculationFabric(String symbol, ArrayList<Double> operands) {
        return operations.get(symbol).init(operands);
    }

    public int getArity(String symbol) {
        return operations.get(symbol).getArity();
    }
}
