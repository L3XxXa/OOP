package ru.nsu.malov.operations;

import java.util.ArrayList;

public class Pow implements Operation {

    @Override
    public double init(ArrayList<Double> arrayList) {
        double a = arrayList.get(0);
        double b = arrayList.get(1);
        double res = 0;
        res = Math.pow(a, b);

        return res;
    }
}
