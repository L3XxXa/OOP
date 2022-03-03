package ru.nsu.malov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Metrics {
    static final int size = 100000;
    static long[] preparedArr = new long[size];
    static List<Long> preparedList = new ArrayList<>();

    private static void prepareArr(int size, long[] arr){
        Arrays.fill(arr, 0, size, 1048571);
        arr[size-1] = 1048561;
    }

    private static void prepareList(int size, List<Long> list){
        for (int i = 0; i < size - 1; i++) {
            list.add(1048571L);
        }
        list.add(1048561L);
    }

    private static void init(){
        preparedArr = new long[size];
        preparedList = new ArrayList<>();
        prepareList(size, preparedList);
        prepareArr(size, preparedArr);
    }

    private static void testMonoThread(MonoThreadPrimeChecker monoThreadPrimeChecker){
        long bestTime = 999999999;
        long testTime = 0;
        long now = 0;
        System.out.println("=====================\nStarting mono thread checker");
        for (int i = 0; i < 15; i++) {
            now = System.nanoTime();
            monoThreadPrimeChecker.monoThreadChecker(preparedArr);
            testTime = System.nanoTime() - now;
            if (bestTime>testTime){
                bestTime = testTime;
            }
        }
        System.out.printf("Best time for mono thread = %d\n", bestTime);
        System.out.println("=====================\n");
    }
    private static void testParallelStream(StreamPrimeChecker streamPrimeChecker) {
        long bestTime = 999999999;
        long testTime = 0;
        long now = 0;
        System.out.println("=====================\nStarting parallel stream checker");
        for (int i = 0; i < 15; i++) {
            now = System.nanoTime();
            streamPrimeChecker.streamChecker(preparedList);
            testTime = System.nanoTime() - now;
            if (bestTime>testTime){
                bestTime = testTime;
            }
        }
        System.out.printf("Best time for parallel stream = %d\n", bestTime);
        System.out.println("=====================\n");
    }
    private static void testMultiThread(MultiThreadPrimeChecker multiThreadPrimeChecker){
        long bestTime = 999999999;
        long testTime = 0;
        long now = 0;
        int THREADS = Runtime.getRuntime().availableProcessors();
        System.out.println("=====================\nStarting multi thread checker");
        for (int i = 1; i <= THREADS; i++) {
            System.out.println("Current amount of threads: " + i);
            for (int j = 0; j < 15; j++) {
                now = System.nanoTime();
                multiThreadPrimeChecker.multiThreadChecker(i, preparedList);
                testTime = System.nanoTime() - now;
                if (bestTime>testTime){
                    bestTime = testTime;
                }
            }
            System.out.println("Best time for " + i + " threads is " + bestTime);
            bestTime = 999999999;
        }
        System.out.println("=====================");
    }

    public static void main(String[] args) {
        MonoThreadPrimeChecker monoThreadPrimeChecker = new MonoThreadPrimeChecker();
        MultiThreadPrimeChecker multiThreadPrimeChecker = new MultiThreadPrimeChecker();
        StreamPrimeChecker streamPrimeChecker = new StreamPrimeChecker();
        init();
        testMonoThread(monoThreadPrimeChecker);
        testParallelStream(streamPrimeChecker);
        testMultiThread(multiThreadPrimeChecker);
    }
}
