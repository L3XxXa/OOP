package ru.nsu.malov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiThreadPrimeCheckerTest {
    MultiThreadPrimeChecker multiThreadPrimeChecker;
    @BeforeAll
    static public void showAmountOfAvailableThreads(){
        System.out.print("Available threads: ");
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
    @BeforeEach
    public void init(){
        multiThreadPrimeChecker = new MultiThreadPrimeChecker();
    }
    @Test
    public void runningWithMoreThanAvailable(){
        long[] arr = {1};
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            multiThreadPrimeChecker.MultiThreadChecker(9, arr);
        });
    }
    @Test
    public void hasCompositeNumber(){
        long[] arr = {1, 2, 4, 5};
        Assertions.assertFalse(multiThreadPrimeChecker.MultiThreadChecker(8, arr));
    }

    @Test
    public void onlyPrimes(){
        long[] arr = {2, 3, 5, 7, 11};
        Assertions.assertTrue(multiThreadPrimeChecker.MultiThreadChecker(8, arr));
    }

}