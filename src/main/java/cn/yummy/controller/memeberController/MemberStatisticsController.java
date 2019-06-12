package cn.yummy.controller.memeberController;

import cn.yummy.entity.member.ConsumptionCharacteristics;
import cn.yummy.entity.member.MemberStatisticsInformation;
import cn.yummy.entity.member.OrderCharacteristics;
import cn.yummy.service.memberService.ConsumerStatisticsService;
import cn.yummy.service.memberService.MemberStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@RestController
@RequestMapping("/member")
public class MemberStatisticsController {
    @Autowired
    private MemberStatisticsService memberStatisticsService;

    @Autowired
    private ConsumerStatisticsService consumerStatisticsService;

    @RequestMapping("/getMemberConsumption")
    public double getMemberConsumption(@RequestParam("account")String account){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        account = (String)attributes.getRequest().getSession().getAttribute("account");
        return memberStatisticsService.getMemberConsumption(account);
    }


    @RequestMapping("/getAbolishedOrdersNum")
    public int getAbolishedOrdersNum(@RequestParam("account")String account){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        account = (String)attributes.getRequest().getSession().getAttribute("account");
        return memberStatisticsService.getAbolishedOrdersNum(account);
    }

    @RequestMapping("/getAcceptedOrdersNum")
    public int getAcceptedOrdersNum(@RequestParam("account")String account){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        account = (String)attributes.getRequest().getSession().getAttribute("account");
        return memberStatisticsService.getAcceptedOrdersNum(account);
    }


    @RequestMapping("/getConsumptionInformation")
    public HashMap<String, Double> getConsumptionInformation(@RequestParam("account")String account){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        account = (String)attributes.getRequest().getSession().getAttribute("account");
        return memberStatisticsService.getConsumptionInformation(account);
    }

    @RequestMapping("/getMemberStatisticsInformation")
    public MemberStatisticsInformation getMemberStatisticsInformation(@RequestParam("account")String account){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        account = (String)attributes.getRequest().getSession().getAttribute("account");
        return memberStatisticsService.getMemberStatisticsInformation(account);
    }




    @RequestMapping("/getOrderCharacteristics")
    public OrderCharacteristics MemberOrderCharacteristics(
            @RequestParam("startTime")String startTime,@RequestParam("endTime")String endTime,@RequestParam("type")String type){

        LocalDate start = LocalDate.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(start.toString()+"   "+end.toString()+"  "+type);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String account = (String)attributes.getRequest().getSession().getAttribute("account");
        return consumerStatisticsService.getOrderCharacteristics(start,end,type,account);
    }

    @RequestMapping("/getConsumptionCharacteristics")
    public ConsumptionCharacteristics getConsumptionCharacteristics(
            @RequestParam("startTime")String startTime,@RequestParam("endTime")String endTime,@RequestParam("type")String type
    ){

        LocalDate start = LocalDate.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(start.toString()+"   "+end.toString()+"  "+type);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String account = (String)attributes.getRequest().getSession().getAttribute("account");
        return consumerStatisticsService.getConsumptionCharacteristics(start,end,type,account);

    }



}
