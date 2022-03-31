package ru.nsu.malov.json.setup;

public class JsonSetupCourier {
    private int deliveryTime;
    private int trunkSize;

    /**
     * JsonSetupCourier class constructor
     * @param deliveryTime - delivery time
     * @param trunkSize - size of courier's trunk
     * */
    public JsonSetupCourier(int deliveryTime, int trunkSize) {
        this.deliveryTime = deliveryTime;
        this.trunkSize = trunkSize;
    }

    /**
     * Get size of the courier's trunk
     * @return courier's trunk size
     * */
    public int getTrunkSize() {
        return trunkSize;
    }

    /**
     * Set courier's trunk size
     * @param trunkSize - courier's trunk size
     * */
    public void setTrunkSize(int trunkSize) {
        this.trunkSize = trunkSize;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
