package cn.yummy.controller.managerController;


import cn.yummy.entity.manager.StatisticsInformation;
import cn.yummy.service.managerService.ManagerStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/manager")
public class ManagerStatisticsController {


    @Autowired
    private ManagerStatisticsService managerStatisticsService;

    @RequestMapping("/getStatistics")
    public StatisticsInformation getStatistics(@RequestParam("account")String account){

            return managerStatisticsService.getStatisticsInformation();
    }



}
