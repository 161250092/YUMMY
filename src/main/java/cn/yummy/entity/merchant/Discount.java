package cn.yummy.entity.merchant;

import java.io.Serializable;
import java.util.List;

public class Discount implements Serializable{
    private long  discountId;
    //识别码
    private String  idCode;

    private double totalPrice;

    private double reducePrice;


    public Discount() {
    }

    public Discount(long discountId, String idCode, double totalPrice, double reducePrice) {
        this.discountId = discountId;
        this.idCode = idCode;
        this.totalPrice = totalPrice;
        this.reducePrice = reducePrice;
    }

    public String getIdCode() {
        return idCode;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getReducePrice() {
        return reducePrice;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setReducePrice(double reducePrice) {
        this.reducePrice = reducePrice;
    }

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }
}
