package ru.nsu.malov.operations;

import java.util.ArrayList;

public class Multiplication implements Operation {
    /**
     * Multiplication
     *
     * @param arrayList - array list with operands
     * @return result of multiplication
     */
    @Override
    public double init(ArrayList<Double> arrayList) {
        return arrayList.get(0) * arrayList.get(1);
    }
}
