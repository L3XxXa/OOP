package ru.nsu.malov.operations;

import java.util.ArrayList;

public class Add implements Operation {
    @Override
    public double init(ArrayList<Double> arrayList) {
        double a = arrayList.get(1);
        double b = arrayList.get(0);
        return a + b;
    }
}
