package ru.nsu.malov;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamPrimeChecker {
    /**
     * method with parallel stream to check the list for prime numbers.
     *
     * @param list - list to check
     * @return true if there is no composite numbers
     * @return false if there is one or more composite numbers
     */
    public boolean streamChecker(List<Long> list) {
        return list.parallelStream().noneMatch(this::notPrime);

    }

    private boolean notPrime(long a) {
        return !PrimeChecker.isPrimeNumber(a);
    }
}
