package ru.nsu.malov.orders;

public class Order {
    private int orderId;
    private String orderStatus;

    /**
     *  Order class constructor
     * @param orderId - order ID
     * */
    public Order(int orderId) {
        this.orderId = orderId;
        orderStatus = "Order #" + orderId + "was made";
    }

    /**
     * Get order status
     * @return order status
     * */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * Set order status
     * @param orderStatus - order status
     * */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Get order ID
     * @return order ID
     * */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Set order ID
     * @param orderId - order ID
     * */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

   /* @Override
    public String toString() {
        return "=================" +
                "\nOrder # " + orderId +
                "\nStatus:" + orderStatus +
                "\n=================\n";
    }*/
}
