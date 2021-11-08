package ru.nsu.malov.operations;

import java.util.ArrayList;

public class Cos implements Operation {
    @Override
    public double init(ArrayList<Double> arrayList) {
        return Math.cos(arrayList.get(0));
    }
}
