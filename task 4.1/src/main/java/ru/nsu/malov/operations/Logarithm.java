package ru.nsu.malov.operations;

import java.util.ArrayList;

public class Logarithm implements Operation {

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
