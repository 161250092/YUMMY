package cn.tycoding.service.memberService;

import cn.tycoding.entity.member.MemberStatisticsInformation;
import cn.tycoding.entity.order.Order;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface MemberStatisticsService {


    public double getMemberConsumption(String account);

    public int getAbolishedOrdersNum(String account);

    public int getAcceptedOrdersNum(String account);

    public HashMap<String,Double> getConsumptionInformation(String account);

    public MemberStatisticsInformation getMemberStatisticsInformation(String account);

}
