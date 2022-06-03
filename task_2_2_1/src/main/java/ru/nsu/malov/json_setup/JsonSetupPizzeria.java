package ru.nsu.malov.json_setup;

public class JsonSetupPizzeria {
    private JsonSetupCook[] cookList;
    private JsonSetupCourier[] courierLists;
    private int storageSize;
    private int orderQueueSize;

    public JsonSetupPizzeria(JsonSetupCook[] cookList, JsonSetupCourier[] courierLists, int storageSize, int orderQueueSize) {
        this.cookList = cookList;
        this.courierLists = courierLists;
        this.storageSize = storageSize;
        this.orderQueueSize = orderQueueSize;
    }

    public JsonSetupCook[] getCookList() {
        return cookList;
    }

    public void setCookList(JsonSetupCook[] cookList) {
        this.cookList = cookList;
    }

    public JsonSetupCourier[] getCourierLists() {
        return courierLists;
    }

    public void setCourierLists(JsonSetupCourier[] courierLists) {
        this.courierLists = courierLists;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(int storageSize) {
        this.storageSize = storageSize;
    }

    public int getOrderQueueSize() {
        return orderQueueSize;
    }

    public void setOrderQueueSize(int orderQueueSize) {
        this.orderQueueSize = orderQueueSize;
    }
}
