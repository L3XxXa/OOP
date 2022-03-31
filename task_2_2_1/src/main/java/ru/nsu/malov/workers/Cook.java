package ru.nsu.malov.workers;


import ru.nsu.malov.orders.Order;
import ru.nsu.malov.orders.OrderQueue;
import ru.nsu.malov.producer_consumer.Producer;

public class Cook implements Producer{
    private final int experience;
    private OrderQueue waitingOrders;
    private OrderQueue storage;
    private int cookingTime = 10000;
    boolean isWorking;
    /**
     * Cook class constructor
     * @param experience - cook's working experience
     * @param storage - pizzeria's storage
     * @param waitingOrders - order's made by customers
     * */
    public Cook(int experience, OrderQueue waitingOrders, OrderQueue storage) {
        this.experience = experience;
        this.waitingOrders = waitingOrders;
        this.storage = storage;
        isWorking = false;
    }


    @Override
    public void produce() {
        cookingTime /= experience;
        Order order;
        while (isWorking){
            order = waitingOrders.getOrder();
            printOrderStatus(order);
            order.setOrderStatus("Cooking");
            try {
                printOrderStatus(order);
                Thread.sleep(cookingTime);
            } catch (InterruptedException e) {
                System.err.println("Can't cook order #" + order.getOrderId());
            }
            order.setOrderStatus("In storage");
            printOrderStatus(order);
            storage.addToQueue(order);
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
