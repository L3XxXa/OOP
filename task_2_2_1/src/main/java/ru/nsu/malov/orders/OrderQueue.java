package ru.nsu.malov.orders;

import java.util.ArrayDeque;
import java.util.Queue;

public class OrderQueue {
    private final int queueSize;
    private Queue<Order> queue;

    /**
     * OrderQueue class constructor
     * @param queueSize - queue size
     * */
    public OrderQueue(int queueSize) {
        this.queueSize = queueSize;
        queue = new ArrayDeque<>();
    }

    public int getQueueSize() {
        return queueSize;
    }

    /**
     * Add order to the queue
     * @param order - order adding
     * */
    public void addToQueue(Order order){
        synchronized (queue){
            while (queueSize == queue.size()){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add(order);
            queue.notifyAll();
        }
    }

    /**
     * Get order from the queue
     * @return order
     * */
    public Order getOrder(){
        synchronized (queue){
            while (queue.isEmpty()){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Order order = queue.poll();
            queue.notifyAll();
            return order;
        }
    }
}
