package ru.nsu.malov.customers;

import ru.nsu.malov.orders.Order;
import ru.nsu.malov.orders.OrderQueue;
import ru.nsu.malov.producer_consumer.Producer;

public class Customer implements Producer {
    private OrderQueue orderQueue;
    private boolean isWorking;

    /**
     * DefaultCustomer class constructor
     * @param orderQueue - queue with orders
     * */
    public Customer(OrderQueue orderQueue){
        this.orderQueue = orderQueue;
        isWorking = false;
    }

    @Override
    public void produce() {
        int orderId = 0;
        while (isWorking) {
            orderId++;
            Order order = new Order(orderId);
            if (orderId>orderQueue.getQueueSize()){
                return;
            }
            order.setOrderStatus("Ordered");
            printOrderStatus(order);
            orderQueue.addToQueue(order);
        }
    }
    @Override
    public void stop() {
        isWorking = false;
    }

    @Override
    public void run() {
        isWorking = true;
        produce();

    }

    private void printOrderStatus(Order order){
        System.out.println("Order #" + order.getOrderId() + "\nStatus: " + order.getOrderStatus());
    }
}
