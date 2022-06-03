package ru.nsu.malov.customers;

import org.junit.jupiter.api.Test;
import ru.nsu.malov.orders.OrderQueue;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void customerCheck() throws InterruptedException {
        OrderQueue orderQueue = new OrderQueue(5);
        Customer customer = new Customer(orderQueue);
        Thread thread = new Thread(customer);
        thread.start();
        Thread.sleep(10000);
        thread.interrupt();
    }

}