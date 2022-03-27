package ru.nsu.malov.customers;

import ru.nsu.malov.orders.Order;
import ru.nsu.malov.orders.OrderQueue;

public class Customers implements Runnable{
    private boolean isOrdering;
    private OrderQueue orderQueue;

    public Customers (OrderQueue orderQueue){
        this.orderQueue = orderQueue;
        isOrdering = false;
    }

    @Override
    public void run() {
        isOrdering = true;
        DefaultCustomer defaultCustomer;
        int i = 0;
        while (isOrdering){
            Order order = new Order(i);
            i++;
            defaultCustomer = new DefaultCustomer(order);
            defaultCustomer.produce();
        }
    }

    public void stopOrdering(){
        isOrdering = false;
    }
}
