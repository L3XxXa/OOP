package ru.nsu.malov;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamPrimeChecker {
    PrimeChecker primeChecker = new PrimeChecker();
    public boolean streamChecker(List<Long> list){
        List<Long> res = list.parallelStream().filter(this :: notPrime).collect(Collectors.toList());
        return res.isEmpty();
    }
    private boolean notPrime(long a){
        return !primeChecker.isPrimeNumber(a);
    }
}
