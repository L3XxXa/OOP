package ru.nsu.malov.workers;

import ru.nsu.malov.orders.Order;
import ru.nsu.malov.orders.OrderQueue;
import ru.nsu.malov.producer_consumer.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Courier extends Worker implements Consumer {
    private final int trunkSize;
    private OrderQueue storage;
    private final int deliveryTime;
    private List<Order> orders;

    public Courier(int workerId, int workingTime, int trunkSize, OrderQueue storage) {
        super(workerId, workingTime);
        this.deliveryTime = workingTime;
        this.storage = storage;
        this.trunkSize = trunkSize;
        orders = new ArrayList<>();
    }


    @Override
    public void running() {
        List<Order> consumedOrders = consume();
        if (consumedOrders == null){
            stopWorking();
        }

    }

    @Override
    public List<Order> consume() {
        Order order = new Order();
        for (int i = 0; i < trunkSize; i++) {
            order = storage.getOrder();
            order.setOrderStatus("Delivering");
            orders.add(order);
        }
        try {
            Thread.sleep(deliveryTime);
            order.setOrderStatus("Delivered");
            return orders;
        } catch (InterruptedException e) {
            System.out.println("Courier can't deliver this order");
            return null;
        }
    }
}
