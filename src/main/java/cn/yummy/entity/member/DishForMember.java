package cn.yummy.entity.member;

import java.io.Serializable;
import java.time.LocalDate;

public class DishForMember implements Serializable{
    private long dishId;

    private String idCode;

    private LocalDate startTime;

    private LocalDate  endTime;

    private String type;

    private String name;

    private double price;

    private int quantity;

    private String description;

    private int selectQuantity=0;

    private boolean canAdd=false;

    private boolean canCancel=false;

    public DishForMember() {

    }

    public DishForMember(long dishId, String idCode) {

        this.dishId = dishId;
        this.idCode = idCode;
        startTime = LocalDate.now();
        endTime = LocalDate.now();
        type="type";
        name="name";
        price=10;
        quantity=100;
        description="nothing";
    }


    public long getDishId() {
        return dishId;
    }

    public String getIdCode() {
        return idCode;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public int getSelectQuantity() {
        return selectQuantity;
    }

    public boolean isCanAdd() {
        return canAdd;
    }

    public boolean isCanCancel() {
        return canCancel;
    }

    public void setDishId(long dishId) {
        this.dishId = dishId;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSelectQuantity(int selectQuantity) {
        this.selectQuantity = selectQuantity;
    }

    public void setCanAdd(boolean canAdd) {
        this.canAdd = canAdd;
    }

    public void setCanCancel(boolean canCancel) {
        this.canCancel = canCancel;
    }
}
