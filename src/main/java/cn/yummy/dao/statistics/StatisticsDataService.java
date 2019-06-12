package cn.yummy.dao.statistics;

import cn.yummy.entity.manager.PlatformCondition;

import java.time.LocalDate;

public interface StatisticsDataService {



    public PlatformCondition getPlatformCondition(LocalDate startTime, LocalDate endTime);


}
