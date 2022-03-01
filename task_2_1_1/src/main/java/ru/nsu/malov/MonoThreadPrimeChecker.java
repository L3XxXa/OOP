package ru.nsu.malov;

import java.util.ArrayList;

public class MonoThreadPrimeChecker {
    /**
     * mono thread method to check the array for prime numbers.
     *
     * @param arr - array to check
     * @return true if there is no composite numbers
     * @return false if there is one or more composite numbers
     */
    public boolean monoThreadChecker(long[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (!PrimeChecker.isPrimeNumber(arr[i])) {
                return false;
            }
        }
        return true;
    }
}
