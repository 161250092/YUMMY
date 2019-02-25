package cn.yummy.service;

import cn.yummy.entity.Result;

public interface BankAccountService {

    public Result out(String account, String password, long orderId,String idCode);

    public Result in(String account,double pay);

    public Result unsubscribeOut(String account,double amount);

}
