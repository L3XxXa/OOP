package ru.nsu.malov.operations;

import java.util.ArrayList;

public class Division implements Operation {
    /**
     * Division
     *
     * @param arrayList - array list with operands
     * @return result of division
     */
    @Override
    public double init(ArrayList<Double> arrayList) {
        double a = arrayList.get(0);
        double b = arrayList.get(1);
        if (b == 0) {
            throw new ArithmeticException();
        }
        double res = 0;
        try {
            res = a / b;
        } catch (ArithmeticException arithmeticException) {
            arithmeticException.printStackTrace();
        }
        return res;
    }

    @Override
    public int getArity() {
        return 2;
    }
}
