package ru.nsu.malov;

public class PrimeChecker {
    public boolean isPrimeNumber(long a) {
        if (a <= 1) {
            return false;
        }
        if (a <= 3) {
            return true;
        }
        if (a % 2 == 0 || a % 3 == 0) {
            return false;
        }
        for (int j = 5; j * j <= a; j += 6) {
            if (a % j == 0 || a % (j + 2) == 0) {
                return false;
            }
        }
        return true;
    }


}
