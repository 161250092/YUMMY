package cn.yummy.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class SearchEntity implements Serializable{

    private String idCode;
    private LocalDate  startTime;
    private LocalDate  endTime;
    private double   lowPrice;
    private double   highPrice;
    private int    lowLevel;
    private int      highLevel;

    public SearchEntity() {
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
