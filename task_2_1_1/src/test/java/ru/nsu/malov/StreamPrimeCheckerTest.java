package ru.nsu.malov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StreamPrimeCheckerTest {
    StreamPrimeChecker streamPrimeChecker;
    List<Long> numbers;
    @BeforeEach
    public void init(){
        streamPrimeChecker = new StreamPrimeChecker();
        numbers = new ArrayList<>();
    }

    @Test
    public void simpleTest(){
        Long[] arr = {1L, 2L, 3L, 4L, 5L};
        numbers = (Arrays.asList(arr));
        Assertions.assertFalse(streamPrimeChecker.streamChecker(numbers));
    }

    @Test
    public void simpleTestAllPrimeNumbers(){
        Long[] arr = {7L, 2L, 3L, 11L, 5L};
        numbers = (Arrays.asList(arr));
        Assertions.assertTrue(streamPrimeChecker.streamChecker(numbers));
    }

    @Test
    public void bigTestOneNonPrime(){
        int size = 10000;
        Long[] arr = new Long[size];
        Arrays.fill(arr, 0, size - 1, 3L);
        arr[9999] = 4L;
        List<Long> list = new ArrayList<>();
        Collections.addAll(list, arr);
        Assertions.assertFalse(streamPrimeChecker.streamChecker(list));
    }

    @Test
    public void bigTestOneBigNonPrime() {
        int size = 10000;
        Long[] arr = new Long[size];
        Arrays.fill(arr, 0, size - 1, 1048571L);
        arr[9999] = 1048561L;
        List<Long> list = new ArrayList<>();
        Collections.addAll(list, arr);
        Assertions.assertFalse(streamPrimeChecker.streamChecker(list));
    }
}