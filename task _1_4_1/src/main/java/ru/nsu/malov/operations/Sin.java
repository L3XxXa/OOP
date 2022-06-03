package ru.nsu.malov.operations;

import java.util.ArrayList;

public class Sin implements Operation {
    /**
     * Sinus
     *
     * @param arrayList - array list with operands
     * @return result of sinus
     */
    @Override
    public double init(ArrayList<Double> arrayList) {
        return Math.sin(arrayList.get(0));
    }

    @Override
    public int getArity() {
        return 1;
    }
}
