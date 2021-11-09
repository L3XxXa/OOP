package ru.nsu.malov.operations;

import java.util.ArrayList;

public class Logarithm implements Operation {
    /**
     * Logarithm
     *
     * @param arrayList - array list with operands
     * @return result of logarithm
     */
    @Override
    public double init(ArrayList<Double> arrayList) {
        double a = arrayList.get(0);
        double res = 0;
        try {
            res = Math.log(a);
        } catch (ArithmeticException arithmeticException) {
            arithmeticException.printStackTrace();
        }
        return res;
    }
}
