package ru.nsu.malov.workers;

import ru.nsu.malov.Order;
import ru.nsu.malov.Pizzeria;

public class Courier implements Runnable{
    private final int deliveryTime;
    private Pizzeria pizzeria;

    public Courier(int deliveryTime, Pizzeria pizzeria) {
        this.deliveryTime = deliveryTime;
        this.pizzeria = pizzeria;
    }


    @Override
    public void run() {
        while (true){
            try {
                Order order = pizzeria.getFromStorage();
                System.out.println(order.toString() + "was taken by courier. Delivering time is: " + deliveryTime);
                Thread.sleep(deliveryTime);
                System.out.println(order.toString() + "was delivered. Have a good meal!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
