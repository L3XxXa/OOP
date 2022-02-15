package ru.nsu.malov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonoThreadPrimeCheckerTest {
    MonoThreadPrimeChecker monoThreadPrimeChecker;
    @BeforeEach
    public void init(){
        monoThreadPrimeChecker = new MonoThreadPrimeChecker();
    }

    @Test
    public void simpleTest(){
        long[] arr = {1, 2, 3, 4, 5};
        Assertions.assertFalse(monoThreadPrimeChecker.monoThreadChecker(arr));
    }

    @Test
    public void simpleTestAllPrimeNumbers(){
        long[] arr = {2, 3, 5, 7, 11};
        Assertions.assertTrue(monoThreadPrimeChecker.monoThreadChecker(arr));
    }
}