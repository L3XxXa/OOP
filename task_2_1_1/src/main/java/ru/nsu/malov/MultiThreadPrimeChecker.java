package ru.nsu.malov;


import java.util.List;

public class MultiThreadPrimeChecker {
    private int THREADS = Runtime.getRuntime().availableProcessors();
    private boolean res = true;

    class ExtendedThread extends Thread {
        List<Long> arr;

        ExtendedThread(List<Long> dividedArr) {
            arr = dividedArr;
        }

        @Override
        public void run() {
            for (Long num : arr) {
                if (!PrimeChecker.isPrimeNumber(num)) {
                    res = false;
                }
            }
        }
    }

    public boolean isRes() {
        return res;
    }

    /**
     * multi thread method to check the array for prime numbers.
     *
     * @param arr    - array to check
     * @param amount - amount of threads
     * @return true if there is no composite numbers
     * @return false if there is one or more composite numbers
     */
    public void MultiThreadChecker(int amount, List<Long> arr) {
        if (amount > THREADS) {
            amount = THREADS;
        }
        int arrSize = arr.size() / amount;
        ExtendedThread[] threads = new ExtendedThread[amount];
        for (int i = 0; i < amount; i++) {
            threads[i] = new ExtendedThread(arr.subList(i * arrSize, (i + 1) * arrSize));
            threads[i].start();
        }

        for (int i = 0; i < amount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


