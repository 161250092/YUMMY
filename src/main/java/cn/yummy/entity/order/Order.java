package cn.yummy.entity.order;



import cn.yummy.entity.member.DishForMember;
import cn.yummy.entity.primitiveType.Location;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private long orderId;

    private String account;

    private String idCode;

    private Location userLocation;

    private LocalDateTime submitTime;

    private LocalDateTime deliveryTime;

    private LocalDateTime orderAcceptedTime;

    private LocalDateTime expectedArriveTime;

    private List<DishForMember> dishes;

    private double totalPrice;

    private OrderState orderState;

    public Order() {

    }

    public Order(long orderId,String idCode,OrderState orderState) {
        this.orderId = orderId;
        this.idCode = idCode;
        this.account="123";
        this.expectedArriveTime =LocalDateTime.now();
        this.orderAcceptedTime = LocalDateTime.now();
        this.userLocation = new Location(3.33,1.11,"鼓楼");
        this.dishes = new ArrayList<DishForMember>();
        DishForMember dish1 = new DishForMember(1,idCode);
        DishForMember dish2 = new DishForMember(2,idCode);
        DishForMember dish3 = new DishForMember(3,idCode);
        dishes.add(dish1);
        dishes.add(dish2);
        dishes.add(dish3);
        totalPrice = 12;
        this.orderState = orderState;
    }





    public long getOrderId() {
        return orderId;
    }

    public String getAccount() {
        return account;
    }

    public String getIdCode() {
        return idCode;
    }

    public Location getUserLocation() {
        return userLocation;
    }

    public LocalDateTime getOrderAcceptedTime() {
        return orderAcceptedTime;
    }

    public LocalDateTime getExpectedArriveTime() {
        return expectedArriveTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public void setUserLocation(Location userLocation) {
        this.userLocation = userLocation;
    }

    public void setOrderAcceptedTime(LocalDateTime orderAcceptedTime) {
        this.orderAcceptedTime = orderAcceptedTime;
    }

    public void setExpectedArriveTime(LocalDateTime expectedArriveTime) {
        this.expectedArriveTime = expectedArriveTime;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public List<DishForMember> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishForMember> dishes) {
        this.dishes = dishes;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }
}
