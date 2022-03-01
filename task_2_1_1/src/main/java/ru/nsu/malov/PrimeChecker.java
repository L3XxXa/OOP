package ru.nsu.malov;

public class PrimeChecker {
    /**
     * method to check is this number prime
     *
     * @param a - number to check
     * @return true if this number is prime
     * @return false if this number is composite
     */
    public static boolean isPrimeNumber(long a) {
        if (a <= 1) {
            return false;
        }
        if (a <= 3) {
            return true;
        }
        if (a % 2 == 0 || a % 3 == 0) {
            return false;
        }
        for (int j = 5; j <= Math.sqrt(a); j += 6) {
            if (a % j == 0 || a % (j + 2) == 0) {
                return false;
            }
        }
        return true;
    }


}
