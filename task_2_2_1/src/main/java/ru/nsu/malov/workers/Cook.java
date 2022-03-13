package ru.nsu.malov.workers;

import ru.nsu.malov.Order;
import ru.nsu.malov.Pizzeria;

public class Cook implements Runnable {
    private final int cookingTime;
    private Pizzeria pizzeria;

    public Cook(int cookingTime, Pizzeria pizzeria) {
        this.cookingTime = cookingTime;
        this.pizzeria = pizzeria;
    }

    @Override
    public void run() {
        while (true){
            try {
                Order order = pizzeria.getOrder();
                System.out.println(order.toString() + "is being cooked");
                Thread.sleep(cookingTime);
                System.out.println(order.toString() + "was cooked");
                pizzeria.placeToStorage(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
