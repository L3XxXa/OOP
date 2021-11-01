package ru.nsu.malov;

public class Operations {
    /**
     * Addition
     * @param a - first number
     * @param b - second number
     * @return (a + b)
     * */
    public double addition(double a, double b) {
        return a + b;
    }
    /**
     * subtraction
     * @param a - first number
     * @param b - second number
     * @return (a - b)
     * */
    public double subtraction(double a, double b) {
        return a - b;
    }
    /**
     * multiplication
     * @param a - first number
     * @param b - second number
     * @return (a * b)
     * */
    public double multiplication(double a, double b) {
        return a * b;
    }
    /**
     * division
     * @param a - first number
     * @param b - second number
     * @return (a / b)
     * */
    public double division(double a, double b) {
        return a / b;
    }
    /**
     * sinus
     * @param a - number
     * @return sin(a)
     * */
    public double sin(double a) {
        return Math.sin(a);
    }
    /**
     * cosine
     * @param a - number
     * @return cos(a)
     * */
    public double cos(double a) {
        return Math.cos(a);
    }
    /**
     * logarithm with base
     * @param a - number
     * @param b - base of logarithm
     * @return logb(a)
     * */
    public double logWithBase(double a, double b) {
        return Math.log(a) / Math.log(b);
    }
    /**
     * natural logarithm
     * @param a - number
     * @return ln(a)
     * */
    public double ln(double a) {
        return Math.log(a);
    }
    /**
     * exponentiation
     * @param a - number
     * @param b - degree (???)
     * @return a^b
     * */
    public double pow(double a, double b) {
        return Math.pow(a, b);
    }
    /**
     * square root
     * @param a - number
     * @return sqrt(a)
     * */
    public double sqrt(double a) {
        return Math.sqrt(a);
    }
}
