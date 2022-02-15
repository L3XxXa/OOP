package ru.nsu.malov;


public class MultiThreadPrimeChecker {
    private final int THREADS = Runtime.getRuntime().availableProcessors();
    public boolean MultiThreadChecker(int amount, long[] arr){
        final boolean[] res = {true};
        if (amount>THREADS) {
            System.out.print("I can't run with this amount of threads. Current available amount is: ");
            System.out.println(THREADS);
            System.out.print("You are trying to run with: ");
            System.out.println(amount);
            throw new IndexOutOfBoundsException();
        }
        Thread [] threads = new Thread[amount];
        Runnable task = new Runnable() {
            @Override
            public void run() {
                PrimeChecker primeChecker = new PrimeChecker();
                for (int i = 0; i < arr.length; i++) {
                    if (!primeChecker.isPrimeNumber(arr[i])) {
                        res[0] = false;
                    }
                }
            }
        };
        for (int i = 0; i < amount; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }
        return res[0];
    }


}

