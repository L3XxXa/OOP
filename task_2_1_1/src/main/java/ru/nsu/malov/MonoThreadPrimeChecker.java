package ru.nsu.malov;

import java.util.ArrayList;

public class MonoThreadPrimeChecker {
    public boolean monoThreadChecker(long[] arr) {
        PrimeChecker primeChecker = new PrimeChecker();
        for (int i = 0; i < arr.length; i++) {
            if (!primeChecker.isPrimeNumber(arr[i])) {
                return false;
            }
        }
        return true;
    }
}
