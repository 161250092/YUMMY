package cn.yummy.entity.yummy;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PayForm implements Serializable{
    private String account;
    private String idCode;
    private String password;
    private long orderId;
    private boolean deliveryRightNow;
    private String dateTime;
    private LocalDateTime deliveryTime;


    public PayForm() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public boolean isDeliveryRightNow() {
        return deliveryRightNow;
    }

    public void setDeliveryRightNow(boolean deliveryRightNow) {
        this.deliveryRightNow = deliveryRightNow;
    }

    public void formatTransfer(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.deliveryTime = LocalDateTime.parse(this.dateTime,df);
    }



}
