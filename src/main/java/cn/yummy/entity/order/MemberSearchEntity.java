package cn.yummy.entity.order;

import java.time.LocalDate;

/**
 * 会员检索订单类
 */
public class MemberSearchEntity {

    private String account;
    private LocalDate startTime;
    private LocalDate  endTime;
    private double   lowPrice;
    private double   highPrice;
    private String  restaurantName="";

    public MemberSearchEntity() {
    }

    public MemberSearchEntity(String account, LocalDate startTime, LocalDate endTime, double lowPrice, double highPrice) {
        this.account = account;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
