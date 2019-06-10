package cn.yummy.service.Impl.member;

import cn.yummy.entity.member.ConsumptionCharacteristics;
import cn.yummy.entity.member.OrderCharacteristics;
import cn.yummy.entity.primitiveType.Location;
import cn.yummy.service.memberService.ConsumerStatisticsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

@Service
public class ConsumerStatisticsServiceImpl implements ConsumerStatisticsService{
    @Override
    public OrderCharacteristics getOrderCharacteristics(LocalDate startTime, LocalDate endTime, String interval) {
        HashMap<LocalDate,Integer> ordersCount  = new HashMap<>();
        HashMap<LocalDate,Double> consumptionCount = new HashMap<>();
        int acceptedOrdersNum = 0;
        for(int i=1;i<31;i++) {
            double temp = Math.random();
            if(temp>0.5) {
                acceptedOrdersNum++;
                ordersCount.put(LocalDate.of(2019, 6, i), (int) (temp * 3));
                consumptionCount.put(LocalDate.of(2019, 6, i), temp * 30);
            }

            else {
                ordersCount.put(LocalDate.of(2019, 6, i),0);
                consumptionCount.put(LocalDate.of(2019, 6, i), 0.0);
            }

        }

        return new OrderCharacteristics(acceptedOrdersNum,ordersCount,consumptionCount);
    }

    @Override
    public ConsumptionCharacteristics getConsumptionCharacteristics(LocalDate startTime, LocalDate endTime, String interval) {
//餐厅偏好
        HashMap<String, Integer > merchantsFavor = new HashMap<>();
//菜品偏好
        HashMap<String,Integer> dishesFavor = new HashMap<>();
//消费区间
        HashMap<Integer,Integer > consumptionIntervals = new HashMap<>();
//点餐地点
        HashMap<Integer,Integer>  consumptionDistance = new HashMap<>();
//点餐时间
        HashMap<LocalTime,Integer> consumptionTimeIntervals = new HashMap<>();

        for(int i=1;i<10;i++){
            int temp =(int)(Math.random()*10);
            merchantsFavor.put("餐厅"+i,temp*10);
            dishesFavor.put("菜品"+i,temp*4);
            consumptionTimeIntervals.put(LocalTime.of((int)(temp*2.4),0),temp*3);
        }
        consumptionIntervals.put(10,5);
        consumptionIntervals.put(30,15);
        consumptionIntervals.put(50,7);
        consumptionIntervals.put(100,1);
        consumptionIntervals.put(1000,0);

        consumptionDistance.put(1,6);
        consumptionDistance.put(2,4);
        consumptionDistance.put(3,3);
        consumptionDistance.put(5,1);
        consumptionDistance.put(7,0);


        return new ConsumptionCharacteristics(merchantsFavor,dishesFavor,consumptionIntervals,consumptionDistance,consumptionTimeIntervals);
    }
}
