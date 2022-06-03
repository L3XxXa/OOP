package ru.nsu.malov.producer_consumer;

/**
 * interface for producers
 * */
public interface Producer extends Runnable{
    /**
     * Produce something
     * */
    void produce();

    void stop();

    void run();
}
