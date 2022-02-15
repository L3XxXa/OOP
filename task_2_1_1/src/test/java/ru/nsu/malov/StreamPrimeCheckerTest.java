package ru.nsu.malov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
}