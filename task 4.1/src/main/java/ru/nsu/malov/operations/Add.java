package ru.nsu.malov.operations;

import java.util.ArrayList;


public class Add implements Operation {
    /**
     * Addition
     *
     * @param arrayList - array list with operands
     * @return result of addition
     */
    @Override
    public double init(ArrayList<Double> arrayList) {
        double a = arrayList.get(1);
        double b = arrayList.get(0);
        return a + b;
    }

    @Override
    public int getArity() {
        return 2;
    }
}
