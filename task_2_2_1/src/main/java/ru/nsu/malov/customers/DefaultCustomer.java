package ru.nsu.malov.customers;

import ru.nsu.malov.orders.Order;
import ru.nsu.malov.orders.OrderQueue;
import ru.nsu.malov.producer_consumer.Producer;

public class DefaultCustomer implements Producer {
    private Order order;
    private OrderQueue orderQueue;
    public DefaultCustomer(Order order, OrderQueue orderQueue){
        this.order = order;
        this.orderQueue = orderQueue;
    }

    @Override
    public void produce() {
        order.setOrderStatus("Ordered");
        orderQueue.addToQueue(order);
    }
}
