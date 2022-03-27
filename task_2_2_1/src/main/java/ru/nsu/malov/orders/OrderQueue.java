package ru.nsu.malov.orders;

import java.util.ArrayDeque;
import java.util.Queue;

public class OrderQueue {
    private final int queueSize;
    private Queue<Order> queue;

    public OrderQueue(int queueSize) {
        this.queueSize = queueSize;
        queue = new ArrayDeque<>();
    }

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
