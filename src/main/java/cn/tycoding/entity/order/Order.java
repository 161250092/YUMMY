package cn.tycoding.entity.order;



import cn.tycoding.entity.merchant.Dish;
import cn.tycoding.entity.merchant.Location;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private long orderId;

    private String account;

    private String idCode;

    private Location userLocation;

    private LocalDateTime orderAcceptedTime;

    private LocalDateTime expectedArriveTime;

    private List<Dish> dishes;

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
        this.dishes = new ArrayList<Dish>();
        Dish dish1 = new Dish(1,idCode);
        Dish dish2 = new Dish(2,idCode);
        Dish dish3 = new Dish(3,idCode);
        dishes.add(dish1);
        dishes.add(dish2);
        dishes.add(dish3);
        totalPrice = 12;
        this.orderState = orderState;
    }

    public Order(long orderId, String account, String idCode, Location userLocation, LocalDateTime orderAcceptedTime, LocalDateTime expectedArriveTime, List<Dish> dishes, double totalPrice, OrderState orderState) {
        this.orderId = orderId;
        this.account = account;
        this.idCode = idCode;
        this.userLocation = userLocation;
        this.orderAcceptedTime = orderAcceptedTime;
        this.expectedArriveTime = expectedArriveTime;
        this.dishes = dishes;
        this.totalPrice = totalPrice;
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

    public List<Dish> getDishes() {
        return dishes;
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

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
}
