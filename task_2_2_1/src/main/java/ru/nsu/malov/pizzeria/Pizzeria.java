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
    private final int TIME = 10000;
    private Customer customer;

    public Pizzeria(OrderQueue orderQueue, OrderQueue storage, int[] cooksExperience, int[] couriersDeliveryTimes, int[] couriersTrunkSize) {
        this.orderQueue = orderQueue;
        this.storage = storage;
        customer = new Customer(orderQueue);
        setupCooks(cooksExperience);
        setupCouriers(couriersDeliveryTimes, couriersTrunkSize);
    }

    public Pizzeria(JsonSetupPizzeria jsonSetupPizzeria){
        this.orderQueue = new OrderQueue(jsonSetupPizzeria.getOrderQueueSize());
        this.storage = new OrderQueue(jsonSetupPizzeria.getStorageSize());
        customer = new Customer(orderQueue);
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
        Thread customerThread = new Thread(customer);
        customerThread.run();
        cooks.stream().map(Thread::new).forEach(Thread::start);
        couriers.stream().map(Thread::new).forEach(Thread::start);
    }

    public void stop(){
        customer.stop();
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException e) {
            System.err.println("Error while closing pizzeria. Customers still sitting in the restaurant");
        }
        System.out.println("Customers left pizzeria.");
        cooks.forEach(Cook::stop);
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException e) {
            System.err.println("Error while closing pizzeria. Cooks want to work more");
        }
        System.out.println("Cooks switched off ovens");
        couriers.forEach(Courier::stop);
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException e) {
            System.err.println("Error while closing pizzeria. Couriers want to deliver more pizza");
        }
        System.out.println("Couriers switched off mopeds' engines");
        System.out.println("Bye-bye. Pizzeria is closed. See you next day!");
        return;
    }
}
