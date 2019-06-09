package cn.yummy.entity.manager;

import java.time.LocalDate;
import java.util.HashMap;

public class PlatformCondition {
    //市场份额
    private double marketShare;
    //收入状态
    private HashMap<LocalDate,Double> incomeCondition;
    //商家数
    private HashMap<LocalDate,Integer> merchantsNum;
   //销售额变化
    private HashMap<LocalDate,Double> salesAmountCondition;

    //用户数
    private int memberNums;
    //消费区间分布
    private HashMap<Double,Integer> consumptionInterval;
    //消费频次分布
    private HashMap<Integer,Integer> consumptionTimesInterval;
    //偏好菜品分布
    private HashMap<String,Integer> dishesFavorInterval;
    //偏好餐厅分布
    private HashMap<String,Integer> merchantsFavorInterval;

}
