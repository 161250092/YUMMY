package cn.yummy.entity.merchant;

import java.time.LocalDate;
import java.util.HashMap;

public class ConsumersCharacteristics {

    //地域分布
    private HashMap<Integer,Integer> locationInterval;
    //消费者总消费金额分布
    private HashMap<Integer,Integer> consumptionAmountInterval;
    //消费次数(留存率)
    private HashMap<String,Integer> consumptionTimes;
    //新客率
    private HashMap<LocalDate,Integer> newConsumersProportion;

    public ConsumersCharacteristics(HashMap<Integer, Integer> locationInterval, HashMap<Integer, Integer> consumptionAmountInterval, HashMap<String, Integer> consumptionTimes, HashMap<LocalDate, Integer> newConsumersProportion) {
        this.locationInterval = locationInterval;
        this.consumptionAmountInterval = consumptionAmountInterval;
        this.consumptionTimes = consumptionTimes;
        this.newConsumersProportion = newConsumersProportion;
    }

    public HashMap<Integer, Integer> getLocationInterval() {
        return locationInterval;
    }

    public void setLocationInterval(HashMap<Integer, Integer> locationInterval) {
        this.locationInterval = locationInterval;
    }

    public HashMap<Integer, Integer> getConsumptionAmountInterval() {
        return consumptionAmountInterval;
    }

    public void setConsumptionAmountInterval(HashMap<Integer, Integer> consumptionAmountInterval) {
        this.consumptionAmountInterval = consumptionAmountInterval;
    }

    public HashMap<LocalDate, Integer> getNewConsumersProportion() {
        return newConsumersProportion;
    }

    public void setNewConsumersProportion(HashMap<LocalDate, Integer> newConsumersProportion) {
        this.newConsumersProportion = newConsumersProportion;
    }

    public HashMap<String, Integer> getConsumptionTimes() {
        return consumptionTimes;
    }

    public void setConsumptionTimes(HashMap<String, Integer> consumptionTimes) {
        this.consumptionTimes = consumptionTimes;
    }
}
