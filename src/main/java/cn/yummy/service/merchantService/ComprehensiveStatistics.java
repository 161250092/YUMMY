package cn.yummy.service.merchantService;

import cn.yummy.entity.merchant.ConsumersCharacteristics;
import cn.yummy.entity.merchant.MarketStatistics;
import cn.yummy.entity.merchant.SalesStatistics;

import java.time.LocalDate;

public interface ComprehensiveStatistics {

    public ConsumersCharacteristics getConsumersCharacteristics(LocalDate startTime, LocalDate endTime, String type,String idCode);

    public SalesStatistics getSalesStatistics(LocalDate startTime, LocalDate endTime, String type,String idCode);

    public MarketStatistics getMarkerStatistics(LocalDate startTime, LocalDate endTime, String type,String idCode);

}
