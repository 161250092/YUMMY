package cn.yummy.entity.member;

import cn.yummy.entity.primitiveType.Location;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

public class ConsumptionCharacteristics {

//餐厅偏好
    private HashMap<String, Integer > merchantsFavor;
//菜品偏好
    private HashMap<String,Integer> dishesFavor;
//消费区间
    private HashMap<Integer,Integer > consumptionIntervals;
//点餐地点
    private HashMap<Integer,Integer>  consumptionDistance;
//点餐时间
    private HashMap<LocalTime,Integer> consumptionTimeIntervals;

    public ConsumptionCharacteristics() {
    }

    public ConsumptionCharacteristics(HashMap<String, Integer> merchantsFavor, HashMap<String, Integer> dishesFavor, HashMap<Integer, Integer> consumptionIntervals, HashMap<Integer, Integer> consumptionDistance, HashMap<LocalTime, Integer> consumptionTimeIntervals) {
        this.merchantsFavor = merchantsFavor;
        this.dishesFavor = dishesFavor;
        this.consumptionIntervals = consumptionIntervals;
        this.consumptionDistance = consumptionDistance;
        this.consumptionTimeIntervals = consumptionTimeIntervals;
    }

    public HashMap<String, Integer> getMerchantsFavor() {
        return merchantsFavor;
    }

    public void setMerchantsFavor(HashMap<String, Integer> merchantsFavor) {
        this.merchantsFavor = merchantsFavor;
    }

    public HashMap<String, Integer> getDishesFavor() {
        return dishesFavor;
    }

    public void setDishesFavor(HashMap<String, Integer> dishesFavor) {
        this.dishesFavor = dishesFavor;
    }

    public HashMap<Integer, Integer> getConsumptionIntervals() {
        return consumptionIntervals;
    }

    public void setConsumptionIntervals(HashMap<Integer, Integer> consumptionIntervals) {
        this.consumptionIntervals = consumptionIntervals;
    }


    public HashMap<Integer, Integer> getConsumptionDistance() {
        return consumptionDistance;
    }

    public void setConsumptionDistance(HashMap<Integer, Integer> consumptionDistance) {
        this.consumptionDistance = consumptionDistance;
    }

    public HashMap<LocalTime, Integer> getConsumptionTimeIntervals() {
        return consumptionTimeIntervals;
    }

    public void setConsumptionTimeIntervals(HashMap<LocalTime, Integer> consumptionTimeIntervals) {
        this.consumptionTimeIntervals = consumptionTimeIntervals;
    }
}
