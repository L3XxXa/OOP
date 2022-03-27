package ru.nsu.malov.producer_consumer;

import ru.nsu.malov.orders.Order;
import ru.nsu.malov.orders.OrderQueue;

public interface Consumer<T> {
    T consume();

}
