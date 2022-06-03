package ru.nsu.malov.operations;

import java.util.ArrayList;

public class LogarithmWithBase implements Operation {
    /**
     * Logarithm with base
     *
     * @param arrayList - array list with operands
     * @return result of logarithm with base
     */
    @Override
    public double init(ArrayList<Double> arrayList) {
        double a = arrayList.get(0);
        double b = arrayList.get(1);
        double res = 0;
        try {
            res = Math.log(a) / Math.log(b);
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
