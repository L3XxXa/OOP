package ru.nsu.malov.producer_consumer;

/**
 * interface for consumers
 * */
public interface Consumer extends Runnable{
    /**
     * Consume something
     * */
    void consume();

    /**
     * Stop working
     * */
    void stop();

    /**
     * Run consumer
     * */
    void run();
}
