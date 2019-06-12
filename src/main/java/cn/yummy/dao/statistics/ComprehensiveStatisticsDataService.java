package cn.yummy.dao.statistics;

import cn.yummy.entity.merchant.ConsumersCharacteristics;
import cn.yummy.entity.merchant.MarketStatistics;
import cn.yummy.entity.merchant.SalesStatistics;

import java.time.LocalDate;

public interface ComprehensiveStatisticsDataService {

    public ConsumersCharacteristics getConsumersCharacteristics(LocalDate startTime, LocalDate endTime, String idCode);

    public SalesStatistics getSalesStatistics(LocalDate startTime, LocalDate endTime, String idCode);

    public MarketStatistics getMarkerStatistics(LocalDate startTime, LocalDate endTime, String idCode);



}
