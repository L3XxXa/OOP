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

    /**
     * Method to get operations
     *
     * @return map key - operator and value - operation
     */
    public Map<String, Operation> getOperations() {
        Map<String, Operation> operationMap = operations;
        return operationMap;
    }

    public double fabric(String symbol, ArrayList<Double> operands){
        return operations.get(symbol).init(operands);
    }
}
