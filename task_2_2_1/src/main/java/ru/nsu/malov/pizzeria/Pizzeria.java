package ru.nsu.malov.pizzeria;

import ru.nsu.malov.customers.Customers;
import ru.nsu.malov.orders.OrderQueue;

public class Pizzeria implements Runnable {
    private OrderQueue orderQueue;
    private OrderQueue storage;
    private final int couriers;
    private final int cooks;
    private Customers customers;
    private boolean isWorking;

    public Pizzeria(int couriers, int cooks){
        this.couriers = couriers;
        this.cooks = cooks;
        orderQueue = new OrderQueue(100);
        storage = new OrderQueue(100);
        customers = new Customers(orderQueue);
        isWorking = false;
    }

    @Override
    public void run() {

    }
}
