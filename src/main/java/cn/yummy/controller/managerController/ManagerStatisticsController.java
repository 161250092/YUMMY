package cn.yummy.controller.managerController;


import cn.yummy.entity.manager.PlatformCondition;
import cn.yummy.entity.manager.StatisticsInformation;
import cn.yummy.service.managerService.ManagerStatisticsService;
import cn.yummy.service.managerService.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("/manager")
public class ManagerStatisticsController {


    @Autowired
    private ManagerStatisticsService managerStatisticsService;

    @Autowired
    private Statistics statistics;

    @RequestMapping("/getStatistics")
    public StatisticsInformation getStatistics(@RequestParam("account")String account){
            return managerStatisticsService.getStatisticsInformation();
    }


    @RequestMapping("/getPlatformStatistics")
    public PlatformCondition getPlatformStatistics(@RequestParam("startTime")String startTime,@RequestParam("endTime")String endTime,@RequestParam("interval")String interval
    ){
        LocalDate start = LocalDate.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(start.toString()+"   "+end.toString()+"  "+interval);
        return  statistics.getPlatformCondition(start,end,interval);
    }


}
