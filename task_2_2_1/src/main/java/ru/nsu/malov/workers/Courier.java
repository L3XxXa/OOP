package ru.nsu.malov.workers;

import ru.nsu.malov.orders.Order;
import ru.nsu.malov.orders.OrderQueue;
import ru.nsu.malov.producer_consumer.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Courier implements Consumer {
    private final int trunkSize;
    private OrderQueue storage;
    private final int deliveryTime;
    private List<Order> orders;
    public boolean isWorking;

    /**
     * Courier class constructor
     * @param storage - pizzeria's storage
     * @param trunkSize - size of the trunk
     * @param deliveryTime - delivery time
     * */
    public Courier(int deliveryTime, int trunkSize, OrderQueue storage) {
        this.deliveryTime = deliveryTime;
        this.storage = storage;
        this.trunkSize = trunkSize;
        orders = new ArrayList<>();
        isWorking = false;
    }

    @Override
    public void consume() {
        Order order = null;
        while (isWorking) {
            for (int i = 0; i < trunkSize; i++) {
                order = storage.getOrder();
                order.setOrderStatus("Took from storage");
                printOrderStatus(order);
                orders.add(order);
            }
            for (Order item : orders) {
                if (orders.isEmpty()){
                    return;
                }
                item.setOrderStatus("Delivering");
                printOrderStatus(item);
                try {
                    Thread.sleep(deliveryTime);
                } catch (InterruptedException e) {
                    System.err.println("Can't deliver order #" + item.getOrderId());
                }
                item.setOrderStatus("Delivered");
                printOrderStatus(item);
            }
        }
    }
    @Override
    public void stop() {
        isWorking = false;
    }

    @Override
    public void run() {
        isWorking = true;
        consume();
    }

    private void printOrderStatus(Order order){
        System.out.println("Order #" + order.getOrderId() + "\nStatus: " + order.getOrderStatus());
    }
}
