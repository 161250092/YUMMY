package cn.yummy.service.memberService;


import cn.yummy.entity.member.ConsumptionCharacteristics;
import cn.yummy.entity.member.OrderCharacteristics;

import java.time.LocalDate;

public interface ConsumerStatisticsService {

    public OrderCharacteristics getOrderCharacteristics(LocalDate startTime,LocalDate endTime,String type,String account);

    public ConsumptionCharacteristics getConsumptionCharacteristics(LocalDate startTime,LocalDate endTime,String type,String account);

}
