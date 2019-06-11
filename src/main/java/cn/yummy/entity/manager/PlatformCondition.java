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

    //用户变化数据
    private HashMap<LocalDate,Integer> memberNums;
    //消费区间分布
    private HashMap<String,Integer> consumptionInterval;
    //消费频次分布
    private HashMap<String,Integer> consumptionTimesInterval;
    //偏好菜品分布
    private HashMap<String,Integer> dishesFavorInterval;
    //偏好餐厅分布
    private HashMap<String,Integer> merchantsFavorInterval;

    public PlatformCondition(double marketShare, HashMap<LocalDate, Double> incomeCondition, HashMap<LocalDate, Integer> merchantsNum, HashMap<LocalDate, Double> salesAmountCondition, HashMap<LocalDate, Integer> memberNums, HashMap<String, Integer> consumptionInterval, HashMap<String, Integer> consumptionTimesInterval, HashMap<String, Integer> dishesFavorInterval, HashMap<String, Integer> merchantsFavorInterval) {
        this.marketShare = marketShare;
        this.incomeCondition = incomeCondition;
        this.merchantsNum = merchantsNum;
        this.salesAmountCondition = salesAmountCondition;
        this.memberNums = memberNums;
        this.consumptionInterval = consumptionInterval;
        this.consumptionTimesInterval = consumptionTimesInterval;
        this.dishesFavorInterval = dishesFavorInterval;
        this.merchantsFavorInterval = merchantsFavorInterval;
    }


    public HashMap<LocalDate, Integer> getMemberNums() {
        return memberNums;
    }

    public void setMemberNums(HashMap<LocalDate, Integer> memberNums) {
        this.memberNums = memberNums;
    }

    public double getMarketShare() {
        return marketShare;
    }

    public void setMarketShare(double marketShare) {
        this.marketShare = marketShare;
    }

    public HashMap<LocalDate, Double> getIncomeCondition() {
        return incomeCondition;
    }

    public void setIncomeCondition(HashMap<LocalDate, Double> incomeCondition) {
        this.incomeCondition = incomeCondition;
    }

    public HashMap<LocalDate, Integer> getMerchantsNum() {
        return merchantsNum;
    }

    public void setMerchantsNum(HashMap<LocalDate, Integer> merchantsNum) {
        this.merchantsNum = merchantsNum;
    }

    public HashMap<LocalDate, Double> getSalesAmountCondition() {
        return salesAmountCondition;
    }

    public void setSalesAmountCondition(HashMap<LocalDate, Double> salesAmountCondition) {
        this.salesAmountCondition = salesAmountCondition;
    }


    public HashMap<String, Integer> getConsumptionInterval() {
        return consumptionInterval;
    }

    public void setConsumptionInterval(HashMap<String, Integer> consumptionInterval) {
        this.consumptionInterval = consumptionInterval;
    }

    public HashMap<String, Integer> getConsumptionTimesInterval() {
        return consumptionTimesInterval;
    }

    public void setConsumptionTimesInterval(HashMap<String, Integer> consumptionTimesInterval) {
        this.consumptionTimesInterval = consumptionTimesInterval;
    }

    public HashMap<String, Integer> getDishesFavorInterval() {
        return dishesFavorInterval;
    }

    public void setDishesFavorInterval(HashMap<String, Integer> dishesFavorInterval) {
        this.dishesFavorInterval = dishesFavorInterval;
    }

    public HashMap<String, Integer> getMerchantsFavorInterval() {
        return merchantsFavorInterval;
    }

    public void setMerchantsFavorInterval(HashMap<String, Integer> merchantsFavorInterval) {
        this.merchantsFavorInterval = merchantsFavorInterval;
    }
}
