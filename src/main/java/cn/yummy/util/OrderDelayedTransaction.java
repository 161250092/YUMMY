package cn.yummy.util;

import cn.yummy.dao.memberDao.MemberOrderDataService;
import cn.yummy.dao.memberDao.MemberOrderDataServiceImpl;

import java.util.Timer;
import java.util.TimerTask;

public class OrderDelayedTransaction {

    private MemberOrderDataService memberOrderDataService = new MemberOrderDataServiceImpl();

    public void delayedTransaction(long orderId){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 处理延时订单
                disposeTimeOut(orderId);
            }
        }, (5 * 60 * 1000));

    }


    private void disposeTimeOut(long orderId){

        System.out.println("调用延时处理订单");
        if(!memberOrderDataService.isPayed(orderId))
            memberOrderDataService.cancelOrder(orderId);

    }

}
