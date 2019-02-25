package cn.yummy.entity.member;

import java.util.HashMap;

public class MemberStatisticsInformation {

    private double totalConsumption;
    private int acceptedOrdersNum;
    private int abolishedOrderNum;
    private HashMap<String, Double> consumptionInformation;

    public MemberStatisticsInformation() {
    }

    public double getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(double totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public int getAcceptedOrdersNum() {
        return acceptedOrdersNum;
    }

    public void setAcceptedOrdersNum(int acceptedOrdersNum) {
        this.acceptedOrdersNum = acceptedOrdersNum;
    }

    public int getAbolishedOrderNum() {
        return abolishedOrderNum;
    }

    public void setAbolishedOrderNum(int abolishedOrderNum) {
        this.abolishedOrderNum = abolishedOrderNum;
    }

    public HashMap<String, Double> getConsumptionInformation() {
        return consumptionInformation;
    }

    public void setConsumptionInformation(HashMap<String, Double> consumptionInformation) {
        this.consumptionInformation = consumptionInformation;
    }
}
