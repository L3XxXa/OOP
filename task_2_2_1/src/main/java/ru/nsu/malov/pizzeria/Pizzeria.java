package ru.nsu.malov.pizzeria;

import ru.nsu.malov.customers.Customers;
import ru.nsu.malov.orders.OrderQueue;
import ru.nsu.malov.workers.Cook;
import ru.nsu.malov.workers.Courier;

import java.util.List;

public class Pizzeria implements Runnable {
    private OrderQueue orderQueue;
    private OrderQueue storage;
    private final List<Cook> cooks;
    private final List<Courier> couriers;
    private Customers customers;

    public Pizzeria(List<Cook> cooks, List<Courier> couriers){
        this.cooks = cooks;
        this.couriers = couriers;
        orderQueue = new OrderQueue(100);
        storage = new OrderQueue(100);
        customers = new Customers(orderQueue);
    }

    @Override
    public void run() {
        Thread threadCustomers = new Thread(customers);
        threadCustomers.start();
        cooks.stream().map(Thread :: new).forEach(Thread::start);
        couriers.stream().map(Thread :: new).forEach(Thread::start);
    }

    public void stop(){
        couriers.forEach(Courier :: stopWorking);
        cooks.forEach(Cook :: stopWorking);
    }
}
