package ru.nsu.malov.operations;

import java.util.ArrayList;

public class Multiplication implements Operation {

    @Override
    public double init(ArrayList<Double> arrayList) {
        return arrayList.get(0) * arrayList.get(1);
    }
}
