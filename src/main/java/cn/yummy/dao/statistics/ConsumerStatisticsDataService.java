package cn.yummy.dao.statistics;

import cn.yummy.entity.member.ConsumptionCharacteristics;
import cn.yummy.entity.member.OrderCharacteristics;

import java.time.LocalDate;

public interface ConsumerStatisticsDataService {

    public OrderCharacteristics getOrderCharacteristics(LocalDate startTime, LocalDate endTime,String account);

    public ConsumptionCharacteristics getConsumptionCharacteristics(LocalDate startTime, LocalDate endTime,String account);

}
