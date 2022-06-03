package ru.nsu.malov.pizzeria;

import ru.nsu.malov.customers.Customer;
import ru.nsu.malov.json.setup.JsonSetupPizzeria;
import ru.nsu.malov.orders.OrderQueue;
import ru.nsu.malov.workers.Cook;
import ru.nsu.malov.workers.Courier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pizzeria {
    private OrderQueue orderQueue;
    private OrderQueue storage;
    private List<Cook> cooks;
    private List<Courier> couriers;
    private final int TIME = 1000;
    private Customer customers;
    private Thread customerThread;
    private Thread[] cooksThread;
    private Thread[] couriersThread;
    public Pizzeria(OrderQueue orderQueue, OrderQueue storage, int[] cooksExperience, int[] couriersDeliveryTimes, int[] couriersTrunkSize) {
        this.orderQueue = orderQueue;
        this.storage = storage;
        customers = new Customer(orderQueue);
        setupCooks(cooksExperience);
        setupCouriers(couriersDeliveryTimes, couriersTrunkSize);
    }

    public Pizzeria(JsonSetupPizzeria jsonSetupPizzeria){
        this.orderQueue = new OrderQueue(jsonSetupPizzeria.getOrderQueueSize());
        this.storage = new OrderQueue(jsonSetupPizzeria.getStorageSize());
        customers = new Customer(orderQueue);
        setupCooks(jsonSetupPizzeria.parseCooks());
        setupCouriers(jsonSetupPizzeria.parseDeliveryTime(), jsonSetupPizzeria.parseTrunkSize());
    }


    private void setupCooks(int [] cooksExperience){
        cooks = new ArrayList<>();
        for (int i = 0; i < cooksExperience.length; i++) {
            Cook cook = new Cook(cooksExperience[i], orderQueue, storage);
            cooks.add(cook);
        }
    }

    private void setupCouriers(int[] couriersDeliveryTime, int[] couriersTrunkSizes) {
        couriers = new ArrayList<>();
        for (int i = 0; i < couriersDeliveryTime.length; i++) {
            Courier courier = new Courier(couriersDeliveryTime[i], couriersTrunkSizes[i], storage);
            couriers.add(courier);
        }
    }

    public void run() {
        System.out.println("Hello.\nWelcome to the pizzeria!");
        System.out.println(Thread.activeCount());
        customerThread = new Thread(customers);
        customerThread.start();
        //customerThread.run();
        cooksThread = new Thread[cooks.size()];
        for (int i = 0; i < cooks.size(); i++) {
            cooksThread[i] = new Thread(cooks.get(i));
        }
        couriersThread = new Thread[couriers.size()];
        for (int i = 0; i < couriers.size(); i++) {
            couriersThread[i] = new Thread(couriers.get(i));
        }
        Arrays.stream(couriersThread).forEach(Thread::start);
        Arrays.stream(cooksThread).forEach(Thread::start);
    }

    public void stop(){
        System.out.println(Thread.activeCount());
        System.out.println(couriers.get(0).isWorking + "\n");
        cooks.forEach(Cook::isWorking);
        //customer.isWorking();
        couriers.forEach(Courier::stop);
        Arrays.stream(couriersThread).forEach(Thread::interrupt);
/*
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException e) {
            System.err.println("Error while closing pizzeria. Couriers want to deliver more pizza");
        }*/
        System.out.println("Couriers switched off mopeds' engines");
        cooks.forEach(Cook::stop);
        Arrays.stream(cooksThread).forEach(Thread::interrupt);
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException e) {
            System.err.println("Error while closing pizzeria. Cooks want to work more");
        }
        System.out.println("Cooks switched off ovens");
        customers.stop();
        customerThread.interrupt();
        //customers.forEach(Customer::stop);
        //customers.stream().map(Thread::new).forEach(Thread::interrupt);
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException e) {
            System.err.println("Error while closing pizzeria. Customers still sitting in the restaurant");
        }
        System.out.println("Customers left pizzeria.");

        System.out.println("Bye-bye. Pizzeria is closed. See you next day!");
        System.out.println(Thread.activeCount());
        System.out.println(couriers.get(0).isWorking + "\n");
        cooks.forEach(Cook::isWorking);


    }
}
