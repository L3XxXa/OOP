package ru.nsu.malov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Metrics {
    static final int size1 = 100;
    static final int size2 = 1000;
    static final int size3 = 10000;
    static final int size4 = 100000;
    static long[] preparedArr100 = new long[size1];
    static long[] preparedArr1000 = new long[size2];
    static long[] preparedArr10000 = new long[size3];
    static long[] preparedArr100000 = new long[size4];
    static List<Long> preparedList100 = new ArrayList<>();
    static List<Long> preparedList1000 = new ArrayList<>();
    static List<Long> preparedList10000 = new ArrayList<>();
    static List<Long> preparedList100000 = new ArrayList<>();

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
        preparedArr100 = new long[size1];
        preparedArr1000 = new long[size2];
        preparedArr10000 = new long[size3];
        preparedArr100000 = new long[size4];
        preparedList100 = new ArrayList<>();
        preparedList1000 = new ArrayList<>();
        preparedList10000 = new ArrayList<>();
        preparedList100000 = new ArrayList<>();
        prepareArr(size1, preparedArr100);
        prepareArr(size2, preparedArr1000);
        prepareArr(size3, preparedArr10000);
        prepareArr(size4, preparedArr100000);
        prepareList(size1, preparedList100);
        prepareList(size2, preparedList1000);
        prepareList(size3, preparedList10000);
        prepareList(size4, preparedList100000);
    }

    private static void testMonoThread(MonoThreadPrimeChecker monoThreadPrimeChecker){
        long time = 999999999;
        long newTime;
        long now = 0;
        System.out.println("==========================");
        System.out.println("Starting mono thread checker");
        for (int i = 100; i <= 100000; i *= 10) {
            if (i == 100){
                for (int j = 0; j < 15; j++) {
                    now = System.nanoTime();
                    monoThreadPrimeChecker.monoThreadChecker(preparedArr100);
                    newTime = System.nanoTime() - now;
                    if (newTime<time){
                        time = newTime;
                    }
                }
                System.out.printf("Time for size == %d: %d\n", i, time);
            }
            time = 999999999;
            if (i == 1000){
                for (int j = 0; j < 15; j++) {
                    now = System.nanoTime();
                    monoThreadPrimeChecker.monoThreadChecker(preparedArr1000);
                    newTime = System.nanoTime() - now;
                    if (newTime<time){
                        time = newTime;
                    }
                }
                System.out.printf("Time for size == %d: %d\n", i, time);
            }
            time = 999999999;
            if (i == 10000){
                for (int j = 0; j < 15; j++) {
                    now = System.nanoTime();
                    monoThreadPrimeChecker.monoThreadChecker(preparedArr10000);
                    newTime = System.nanoTime() - now;
                    if (newTime<time){
                        time = newTime;
                    }
                }
                System.out.printf("Time for size == %d: %d\n", i, time);
            }
            time = 999999999;
            if (i == 100000){
                for (int j = 0; j < 15; j++) {
                    now = System.nanoTime();
                    monoThreadPrimeChecker.monoThreadChecker(preparedArr100000);
                    newTime = System.nanoTime() - now;
                    if (newTime<time){
                        time = newTime;
                    }
                }
                System.out.printf("Time for size == %d: %d\n", i, time);
            }
        }
        System.out.println("==========================");
    }
    private static void testParallelStream(StreamPrimeChecker streamPrimeChecker){
        long time = 999999999;
        long newTime;
        long now = 0;
        System.out.println("==========================");
        System.out.println("Starting Parallel Stream checker");
        for (int i = 100; i <= 100000; i *= 10) {
            if (i == 100) {
                for (int j = 0; j < 15; j++) {
                    now = System.nanoTime();
                    streamPrimeChecker.streamChecker(preparedList100);
                    newTime = System.nanoTime() - now;
                    if (newTime < time) {
                        time = newTime;
                    }
                }
                System.out.printf("Time for size == %d: %d\n", i, time);
            }
            time = 999999999;
            if (i == 1000) {
                for (int j = 0; j < 15; j++) {
                    now = System.nanoTime();
                    streamPrimeChecker.streamChecker(preparedList1000);
                    newTime = System.nanoTime() - now;
                    if (newTime < time) {
                        time = newTime;
                    }
                }
                System.out.printf("Time for size == %d: %d\n", i, time);
            }
            time = 999999999;
            if (i == 10000) {
                for (int j = 0; j < 15; j++) {
                    now = System.nanoTime();
                    streamPrimeChecker.streamChecker(preparedList10000);
                    newTime = System.nanoTime() - now;
                    if (newTime < time) {
                        time = newTime;
                    }
                }
                System.out.printf("Time for size == %d: %d\n", i, time);
            }
            time = 999999999;
            if (i == 100000) {
                for (int j = 0; j < 15; j++) {
                    now = System.nanoTime();
                    streamPrimeChecker.streamChecker(preparedList100000);
                    newTime = System.nanoTime() - now;
                    if (newTime < time) {
                        time = newTime;
                    }
                }
                System.out.printf("Time for size == %d: %d\n", i, time);
            }
        }
        System.out.println("==========================");
    }

    private static void testMultiThread(MultiThreadPrimeChecker multiThreadPrimeChecker){
        long time = 999999999;
        long newTime;
        long now = 0;
        int threads = 8;
        System.out.println("==========================");
        System.out.println("Starting Multi Thread checker");
        for (int i = 100; i <= 100000; i *= 10) {
            if (i == 100) {
                for (int j = 0; j < 15; j++) {
                    now = System.nanoTime();
                    multiThreadPrimeChecker.multiThreadChecker(8, preparedList100);
                    newTime = System.nanoTime() - now;
                    if (newTime < time) {
                        time = newTime;
                    }
                }
                System.out.printf("Time for size == %d: %d\n", i, time);
            }
            time = 999999999;
            if (i == 1000) {
                for (int j = 0; j < 15; j++) {
                    now = System.nanoTime();
                    multiThreadPrimeChecker.multiThreadChecker(8, preparedList1000);
                    newTime = System.nanoTime() - now;
                    if (newTime < time) {
                        time = newTime;
                    }
                }
                System.out.printf("Time for size == %d: %d\n", i, time);
            }
            time = 999999999;
            if (i == 10000) {
                for (int j = 0; j < 15; j++) {
                    now = System.nanoTime();
                    multiThreadPrimeChecker.multiThreadChecker(8, preparedList10000);
                    newTime = System.nanoTime() - now;
                    if (newTime < time) {
                        time = newTime;
                    }
                }
                System.out.printf("Time for size == %d: %d\n", i, time);
            }
            time = 999999999;
            if (i == 100000) {
                for (int j = 0; j < 15; j++) {
                    now = System.nanoTime();
                    multiThreadPrimeChecker.multiThreadChecker(8, preparedList100000);
                    newTime = System.nanoTime() - now;
                    if (newTime < time) {
                        time = newTime;
                    }
                }
                System.out.printf("Time for size == %d: %d\n", i, time);
            }
            time = 999999999;
        }
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
