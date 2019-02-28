package cn.yummy.service;

import cn.yummy.entity.primitiveType.Result;
import cn.yummy.entity.yummy.PayForm;

import java.time.LocalDateTime;

public interface BankAccountService {

    public Result payForOrder(PayForm payForm);

    public Result out(String account, String password, long orderId,String idCode,LocalDateTime deliveryTime);

    public Result in(String account,double pay);

    public Result unsubscribeOut(String account,double amount);

}
