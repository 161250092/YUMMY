package cn.yummy.entity.member;

import cn.yummy.entity.primitiveType.Location;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ConsumptionCharacteristics {

//餐厅偏好
    private HashMap<String, Integer > merchantsFavor;
//菜品偏好
    private HashMap<String,Integer> dishesFavor;
//消费区间
    private HashMap<Integer,Integer > consumptionIntervals;
//点餐地点
    private HashMap<Location,Integer>  consumptionAreas;
//点餐时间
    private HashMap<LocalDateTime,Integer> consumptionTimeIntervals;

    public ConsumptionCharacteristics() {
    }

    public ConsumptionCharacteristics(HashMap<String, Integer> merchantsFavor, HashMap<String, Integer> dishesFavor, HashMap<Integer, Integer> consumptionIntervals, HashMap<Location, Integer> consumptionAreas, HashMap<LocalDateTime, Integer> consumptionTimeIntervals) {
        this.merchantsFavor = merchantsFavor;
        this.dishesFavor = dishesFavor;
        this.consumptionIntervals = consumptionIntervals;
        this.consumptionAreas = consumptionAreas;
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

    public HashMap<Location, Integer> getConsumptionAreas() {
        return consumptionAreas;
    }

    public void setConsumptionAreas(HashMap<Location, Integer> consumptionAreas) {
        this.consumptionAreas = consumptionAreas;
    }

    public HashMap<LocalDateTime, Integer> getConsumptionTimeIntervals() {
        return consumptionTimeIntervals;
    }

    public void setConsumptionTimeIntervals(HashMap<LocalDateTime, Integer> consumptionTimeIntervals) {
        this.consumptionTimeIntervals = consumptionTimeIntervals;
    }
}
