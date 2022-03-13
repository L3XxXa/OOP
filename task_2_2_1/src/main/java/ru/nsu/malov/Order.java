package ru.nsu.malov;

public class Order {
    private final int orderId;
    private boolean isReady;

    public Order(int orderId) {
        this.orderId = orderId;
        isReady = false;
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "=================\n"+
                "Order #" +
                orderId +
                "\nStatus: ";
    }
}
