package ru.nsu.malov.orders;

public class Order {
    private int orderId;
    private String orderStatus;

    public Order(int orderId) {
        this.orderId = orderId;
    }

    public Order(){

    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "=================" +
                "Order # " + orderId +
                "\nStatus:" + orderStatus +
                "=================";
    }
}
