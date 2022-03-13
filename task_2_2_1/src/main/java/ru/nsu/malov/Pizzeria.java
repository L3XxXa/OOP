package ru.nsu.malov;

import ru.nsu.malov.workers.Cook;
import ru.nsu.malov.workers.Courier;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Pizzeria {
    private Queue<Order> orders;
    private Queue<Order> storage;
    private final int storageCapacity;
    private Order order;
    private int totalOrders;

    public int getTotalOrders() {
        return totalOrders;
    }

    public Pizzeria(int storageCapacity) {
        orders = new ArrayDeque<>();
        storage = new ArrayDeque<>();
        this.storageCapacity = storageCapacity;
        totalOrders = 1;
    }


    public synchronized void makeOrder(){
        order=new Order(totalOrders);
        orders.add(order);
        totalOrders+=1;
        System.out.println(order.toString() + "was added to the queue");
    }

    public synchronized Order getOrder() throws InterruptedException {
        while (orders.isEmpty()){
            wait();
        }
        Order order = orders.remove();
        System.out.println(order.toString() + "was taken by cook");
        notifyAll();
        return order;
    }

    public synchronized void placeToStorage(Order order) throws InterruptedException {
        while (storage.size() == storageCapacity){
            wait();
        }
        storage.add(order);
        System.out.println(order.toString() + "was placed to the storage");
        notifyAll();
    }

    public synchronized Order getFromStorage() throws InterruptedException {
        while (storage.isEmpty()){
            wait();
        }
        Order order = storage.remove();
        System.out.println(order.toString() + "was taken by courier");
        notifyAll();
        return order;
    }

    public void init(int cooksAmount, int couriersAmount, int ordersAmount){
        for (int i = 0; i < ordersAmount; i++) {
            makeOrder();
        }
        ExecutorService cooks = Executors.newFixedThreadPool(cooksAmount);
        ExecutorService couriers = Executors.newFixedThreadPool(couriersAmount);
        for (int i = 0; i < cooksAmount; i++) {
            Cook cook = new Cook(10, this);
            cooks.submit(cook);
        }
        for (int i = 0; i < couriersAmount; i++) {
            Courier courier = new Courier(10, this);
            couriers.submit(courier);
            System.out.println("i");
        }

    }
}
