package cn.yummy.dao.statistics;

import cn.yummy.entity.manager.PlatformCondition;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

@Service
public class StatisticsDataServiceImpl implements  StatisticsDataService{

    private static int[] salesAmountInterval = {0,10,15,20,30,50,100,300,500};
    @Override
    public PlatformCondition getPlatformCondition(LocalDate startTime, LocalDate endTime) {

        double marketShare = 0.12;
        //收入状态
        HashMap<LocalDate,Double> incomeCondition = new HashMap<>();
        //商家数
        HashMap<LocalDate,Integer> merchantsNum = new HashMap<>();
        //销售额变化
        HashMap<LocalDate,Double> salesAmountCondition = new HashMap<>();
        //用户变化数据
        HashMap<LocalDate,Integer> memberNums = new HashMap<>();


        //消费区间分布
        HashMap<String,Integer> consumptionInterval = new HashMap<>();
        //消费频次分布
        HashMap<String,Integer> consumptionTimesInterval = new HashMap<>();
        //偏好菜品分布
        HashMap<String,Integer> dishesFavorInterval = new HashMap<>();
        //偏好餐厅分布
        HashMap<String,Integer> merchantsFavorInterval = new HashMap<>();


        Period period = Period.between(endTime,startTime);
        int days = period.getDays();



        //收入状态
        //销售额变化
        //用户变化数据
        int initNums = 14;
        for(int i=0;i<days;i++){
            LocalDate localDate = startTime.plusDays(i);
            incomeCondition.put(startTime.plusDays(i),Util.getPlatformVolume(localDate));
            salesAmountCondition.put(localDate,Util.getSalesVolume(localDate));

            int temp = (int)(Math.random()*3);
            initNums +=temp;
            memberNums.put(localDate,initNums);

        }


        //偏好菜品分布
        Util.dishFavor(dishesFavorInterval);
        //偏好餐厅
        Util.merchantFavor(merchantsFavorInterval);


        //消费区间分布
        //消费频次分布

        for(int i=0;i<salesAmountInterval.length-1;i++){
            int total = Util.getNumInConsumptionInterval(salesAmountInterval[i],salesAmountInterval[i+1]);
            consumptionInterval.put(salesAmountInterval[i]+"+",total);
        }
        Util.getNumInConsumptionTimesInterval(consumptionTimesInterval);
        return new PlatformCondition(marketShare,incomeCondition,merchantsNum,salesAmountCondition,memberNums,consumptionInterval,consumptionTimesInterval,
                dishesFavorInterval,merchantsFavorInterval);
    }




}
