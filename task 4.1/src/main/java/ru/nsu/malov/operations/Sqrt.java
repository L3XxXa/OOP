package ru.nsu.malov.operations;

import java.util.ArrayList;

public class Sqrt implements Operation {
    /**
     * Square root
     *
     * @param arrayList - array list with operands
     * @return result of square root
     */
    @Override
    public double init(ArrayList<Double> arrayList) {
        double a = arrayList.get(0);
        double res = 0;
        try {
            res = Math.sqrt(a);
        } catch (ArithmeticException arithmeticException) {
            arithmeticException.printStackTrace();
        }
        return res;
    }

    @Override
    public int getArity() {
        return 1;
    }
}
