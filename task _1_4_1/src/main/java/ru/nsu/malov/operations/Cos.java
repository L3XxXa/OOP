package ru.nsu.malov.operations;

import java.util.ArrayList;

public class Cos implements Operation {
    /**
     * Cosines
     *
     * @param arrayList - array list with operand
     * @return result of cosines
     */
    @Override
    public double init(ArrayList<Double> arrayList) {
        return Math.cos(arrayList.get(0));
    }

    @Override
    public int getArity() {
        return 1;
    }
}
