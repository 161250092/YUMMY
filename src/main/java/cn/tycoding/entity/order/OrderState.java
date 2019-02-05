package cn.tycoding.entity.order;

import java.io.Serializable;

public class OrderState implements Serializable{

    private boolean isPayed;

    private boolean isReceived;

    private boolean isAbolished;

    public OrderState(boolean isPayed, boolean isReceived, boolean isAbolished) {
        this.isPayed = isPayed;
        this.isReceived = isReceived;
        this.isAbolished = isAbolished;
    }

    public OrderState() {
    }

    public boolean isPayed() {
        return isPayed;
    }

    public boolean isReceived() {
        return isReceived;
    }

    public boolean isAbolished() {
        return isAbolished;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    public void setReceived(boolean received) {
        isReceived = received;
    }

    public void setAbolished(boolean abolished) {
        isAbolished = abolished;
    }
}
