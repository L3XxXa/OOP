package ru.nsu.malov.operations;

import java.util.ArrayList;

public class Sin implements Operation {

    @Override
    public double init(ArrayList<Double> arrayList) {
        return Math.sin(arrayList.get(0));
    }
}
