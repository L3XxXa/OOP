package ru.nsu.malov.operations;

import java.util.ArrayList;

public class Subtraction implements Operation {
    /**
     * Subtraction
     *
     * @param arrayList - array list with operands
     * @return result of subtraction
     */
    @Override
    public double init(ArrayList<Double> arrayList) {
        return arrayList.get(0) - arrayList.get(1);
    }

    @Override
    public int getArity() {
        return 2;
    }
}
