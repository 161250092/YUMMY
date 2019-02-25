package cn.yummy.util;

import cn.yummy.dao.merchantDao.MerchantInformationDataService;
import cn.yummy.dao.merchantDao.MerchantInformationDataServiceImpl;
import cn.yummy.entity.merchant.Location;
import cn.yummy.entity.order.Order;



import java.time.LocalDateTime;

public class ComputeArrivalTime {

    private MerchantInformationDataService merchantInformationDataService = new MerchantInformationDataServiceImpl();

    public double distance  = 0;

    public boolean accessible;

    public LocalDateTime computeArrivalTime(Order order){

        Location source = merchantInformationDataService.getMerchantInfo(order.getIdCode()).getLocation();
        Location destination = order.getUserLocation();

        double distance = this.getDistance(source,destination);
        long minutes = (long)(distance/0.2 + 20);

        LocalDateTime submitTime = order.getSubmitTime();
        LocalDateTime expectTime = submitTime.plusMinutes(minutes);
System.out.println(minutes);
        accessible = minutes < 90;

        return expectTime;
    }


    private double getDistance(Location source,Location destination){
        double Lat1 = rad(source.getLat()); // 纬度

        double Lat2 = rad(destination.getLat());

        double a = Lat1 - Lat2;//两点纬度之差

        double b = rad(source.getLng()) - rad(destination.getLng()); //经度之差

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b / 2), 2)));//计算两点距离的公式

        s = s * 6378137.0;//弧长乘地球半径（半径为米）

        s = Math.round(s * 10000d) / 10000d;//精确距离的数值
        s = s/1000;//将单位转换为km，如果想得到以米为单位的数据 就不用除以1000

        distance = s;
        return s;
    }

    private double rad(double d){
        return d*Math.PI/180;
    }


}
