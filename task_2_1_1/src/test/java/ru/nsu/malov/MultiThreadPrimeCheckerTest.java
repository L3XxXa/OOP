package ru.nsu.malov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class MultiThreadPrimeCheckerTest {
    MultiThreadPrimeChecker multiThreadPrimeChecker;

    @BeforeEach
    public void init() {
        multiThreadPrimeChecker = new MultiThreadPrimeChecker();
    }

    @Test
    public void bigTestOneBigNonPrime() {
        int size = 10000;
        Long[] arr = new Long[size];
        Arrays.fill(arr, 0, size - 1, 1048571L);
        arr[9999] = 1048561L;
        List<Long> list = new ArrayList<>();
        Collections.addAll(list, arr);
        multiThreadPrimeChecker.multiThreadChecker(8, list);
        Assertions.assertFalse(multiThreadPrimeChecker.isRes());
    }

    @Test
    public void bigTestOneNonPrime() {
        int size = 10000;
        Long[] arr = new Long[size];
        Arrays.fill(arr, 0, size - 1, 3L);
        arr[9999] = 4L;
        List<Long> list = new ArrayList<>();
        Collections.addAll(list, arr);
        multiThreadPrimeChecker.multiThreadChecker(8, list);
        Assertions.assertFalse(multiThreadPrimeChecker.isRes());
    }


}