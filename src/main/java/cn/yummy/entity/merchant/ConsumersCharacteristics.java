package cn.yummy.entity.merchant;

import java.time.LocalDate;
import java.util.HashMap;

public class ConsumersCharacteristics {

    //地域分布
    private HashMap<Integer,Integer> locationInterval;
    //消费者总消费金额分布
    private HashMap<Integer,Integer> consumptionAMountInterval;
    //消费次数(留存率)
    private HashMap<String,Integer> consumptionTimes;
    //新客率
    private HashMap<LocalDate,Integer> newConsumersProportion;


}
