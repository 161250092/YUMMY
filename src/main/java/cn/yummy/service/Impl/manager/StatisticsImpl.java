package cn.yummy.service.Impl.manager;


import cn.yummy.entity.manager.PlatformCondition;
import cn.yummy.service.managerService.Statistics;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;

@Service
public class StatisticsImpl  implements Statistics{


    @Override
    public PlatformCondition getPlatformCondition(LocalDate startTime, LocalDate endTime, String interval) {
        //市场份额
        double marketShare = 0.35;
        //收入状态
        HashMap<LocalDate,Double> incomeCondition = new HashMap<>();
        for(int i=1;i<30;i++){
            int temp = (int)(Math.random()*10000);
            incomeCondition.put(LocalDate.of(2019,6,i),20000.0+temp);
        }

        //商家数
        HashMap<LocalDate,Integer> merchantsNum = new HashMap<>();
        int initNums = 500;
        for(int i=1;i<30;i++){
            int temp = (int)(Math.random()*100);
            initNums +=temp;
            merchantsNum.put(LocalDate.of(2019,6,i),initNums);
        }

        //销售额变化
        HashMap<LocalDate,Double> salesAmountCondition = new HashMap<>();
        for(int i=1;i<30;i++){
            int temp = (int)(Math.random()*100000);
            salesAmountCondition.put(LocalDate.of(2019,6,i),200000.0+temp);
        }

        //用户数
        int memberNums = 10452;
        //消费区间分布
        HashMap<String,Integer> consumptionInterval = new HashMap<>();
        for(int i=1;i<=10;i++){
            int temp =(int)(Math.random()*5000);

            if(i<=4)
                consumptionInterval.put(i*10.0+"",temp+10000);
            else
                consumptionInterval.put(i*10.0+"",temp+70000);

        }
       // consumptionInterval.put(100.0+"+",9875);


        //消费频次分布
        HashMap<String,Integer> consumptionTimesInterval = new HashMap<>();
        consumptionTimesInterval.put("1",1345);
        consumptionTimesInterval.put("2",4124);
        consumptionTimesInterval.put("3",5355);
        consumptionTimesInterval.put("5",8731);
        consumptionTimesInterval.put("10",15156);
        consumptionTimesInterval.put("15",21356);
        consumptionTimesInterval.put("20",12000);
        consumptionTimesInterval.put("30",4300);
        consumptionTimesInterval.put("50",331);


        //偏好菜品分布
        HashMap<String,Integer> dishesFavorInterval = new HashMap<>();
        for(int i=1;i<15;i++){
            int temp = (int)(Math.random()*1000);
            dishesFavorInterval.put("菜品"+i,500+temp);
        }


        //偏好餐厅分布
        HashMap<String,Integer> merchantsFavorInterval = new HashMap<>();
        for(int i=1;i<15;i++){
            int temp = (int)(Math.random()*1000);
            merchantsFavorInterval.put("餐厅"+i,500+temp);
        }



        return new PlatformCondition(marketShare,incomeCondition,merchantsNum,salesAmountCondition,memberNums,consumptionInterval,consumptionTimesInterval,dishesFavorInterval,merchantsFavorInterval);
    }



}
