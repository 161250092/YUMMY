package cn.yummy.controller.merchantController;


import cn.yummy.entity.merchant.ConsumersCharacteristics;
import cn.yummy.entity.merchant.MarketStatistics;
import cn.yummy.entity.merchant.MerchantStatistics;
import cn.yummy.entity.merchant.SalesStatistics;
import cn.yummy.service.merchantService.ComprehensiveStatistics;
import cn.yummy.service.merchantService.MerchantStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/merchant")
public class MerchantStatisticsController {

    @Autowired
    private MerchantStatisticsService merchantStatisticsService;

    @Autowired
    private ComprehensiveStatistics comprehensiveStatistics;

    @RequestMapping("/getMerchantStatistics")
    public MerchantStatistics getMerchantStatistics(@RequestParam("account")String account){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        account = (String)attributes.getRequest().getSession().getAttribute("account");
        return merchantStatisticsService.getMerchantStatistics(account);
    }


    @RequestMapping("/getConsumersCharacteristics")
    public ConsumersCharacteristics getConsumersCharacteristics(@RequestParam("startTime")String startTime,@RequestParam("endTime")String endTime,@RequestParam("type")String type)
    {
        LocalDate start = LocalDate.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String account = (String)attributes.getRequest().getSession().getAttribute("account");

        return comprehensiveStatistics.getConsumersCharacteristics(start,end,type,account);
    }

    @RequestMapping("/getSalesStatistics")
    public SalesStatistics getSalesStatistics(@RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime, @RequestParam("type")String type)
    {
        LocalDate start = LocalDate.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String account = (String)attributes.getRequest().getSession().getAttribute("account");

        return comprehensiveStatistics.getSalesStatistics(start,end,type,account);
    }

    @RequestMapping("/getMarketStatistics")
    public MarketStatistics getMarketStatistics(@RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime, @RequestParam("type")String type)
    {
        LocalDate start = LocalDate.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String account = (String)attributes.getRequest().getSession().getAttribute("account");

        return comprehensiveStatistics.getMarkerStatistics(start,end,type,account);
    }




}
