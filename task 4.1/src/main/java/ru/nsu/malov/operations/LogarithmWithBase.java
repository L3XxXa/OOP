package ru.nsu.malov.operations;

import java.util.ArrayList;

public class LogarithmWithBase implements Operation {

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
}
