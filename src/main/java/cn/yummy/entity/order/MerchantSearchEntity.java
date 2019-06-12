package cn.yummy.entity.order;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 商家检索订单类
 */
public class MerchantSearchEntity implements Serializable{

    private String idCode;
    private LocalDate  startTime;
    private LocalDate  endTime;
    private double   lowPrice;
    private double   highPrice;
    private int    lowLevel;
    private int      highLevel;

    public MerchantSearchEntity() {
    }

    public MerchantSearchEntity(String idCode, LocalDate startTime, LocalDate endTime) {
        this.idCode = idCode;
        this.startTime = startTime;
        this.endTime = endTime;

        lowPrice = 0;
        highLevel =Integer.MAX_VALUE;
        lowPrice = 1;
        highLevel = 10;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public int getLowLevel() {
        return lowLevel;
    }

    public int getHighLevel() {
        return highLevel;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public void setLowLevel(int lowLevel) {
        this.lowLevel = lowLevel;
    }

    public void setHighLevel(int highLevel) {
        this.highLevel = highLevel;
    }
}
