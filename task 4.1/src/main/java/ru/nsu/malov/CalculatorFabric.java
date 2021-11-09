package ru.nsu.malov;

import ru.nsu.malov.operations.*;

import java.util.ArrayList;

public class CalculatorFabric {
    /**
     * Method to make an array list with all operations
     *
     * @return list with operations
     */
    public ArrayList<Operation> operationFabric() {
        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(new Add());
        operations.add(new Cos());
        operations.add(new Division());
        operations.add(new Logarithm());
        operations.add(new LogarithmWithBase());
        operations.add(new Multiplication());
        operations.add(new Pow());
        operations.add(new Sin());
        operations.add(new Sqrt());
        operations.add(new Subtraction());
        return operations;
    }
}
