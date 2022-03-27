package ru.nsu.malov.workers;


import ru.nsu.malov.orders.Order;
import ru.nsu.malov.orders.OrderQueue;
import ru.nsu.malov.producer_consumer.Producer;

public class Cook extends Worker implements Producer{
    private final int experiene;
    private OrderQueue waitingOrders;
    private OrderQueue storage;
    private final int fullCookingTime = 10000;

    public Cook(int workerId, int workerInfo) {
        super(workerId, workerInfo);
        this.experiene = workerInfo;
    }

    @Override
    public void running() {
        produce();
    }

    @Override
    public void produce() {
        int cookingTime = fullCookingTime / experiene;
        Order order = waitingOrders.getOrder();
        order.setOrderStatus("Cooking");
        try {
            Thread.sleep(cookingTime);
            order.setOrderStatus("Cooked");
            storage.addToQueue(order);
        } catch (InterruptedException e) {
            System.out.println("Cook can't cook pizza");
        }

    }


}
