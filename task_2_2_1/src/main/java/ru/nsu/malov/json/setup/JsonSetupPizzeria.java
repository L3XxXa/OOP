package ru.nsu.malov.json.setup;

public class JsonSetupPizzeria {
    private JsonSetupCook[] cookList;
    private JsonSetupCourier[] courierLists;
    private int storageSize;
    private int orderQueueSize;

    /**
     * JsonSetupPizzeria class constructor
     * @param cookList - list of all cooks
     * @param courierLists - list of all couriers
     * @param orderQueueSize - queue for orders size
     * @param storageSize - storage size
     * */
    public JsonSetupPizzeria(int storageSize, int orderQueueSize, JsonSetupCook[] cookList, JsonSetupCourier[] courierLists) {
        this.orderQueueSize = orderQueueSize;
        this.storageSize = storageSize;
        this.cookList = cookList;
        this.courierLists = courierLists;
    }

    public JsonSetupCook[] getCookList() {
        return cookList;
    }

    public JsonSetupCourier[] getCourierLists() {
        return courierLists;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public int getOrderQueueSize() {
        return orderQueueSize;
    }
    public int[] parseCooks(){
        int[] cookingExperience = new int[cookList.length];
        for (int i = 0; i < cookingExperience.length; i++) {
            cookingExperience[i] = cookList[i].getCookingExperience();
        }
        return cookingExperience;
    }

    public int[] parseDeliveryTime(){
        int[] deliveryTime = new int[courierLists.length];
        for (int i = 0; i < deliveryTime.length; i++) {
            deliveryTime[i] = courierLists[i].getDeliveryTime();
        }
        return deliveryTime;
    }

    public int[] parseTrunkSize(){
        int[] trunkSize = new int[courierLists.length];
        for (int i = 0; i < trunkSize.length; i++) {
            trunkSize[i] = courierLists[i].getTrunkSize();
        }
        return trunkSize;
    }
}
