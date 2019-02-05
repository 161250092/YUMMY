package cn.tycoding.service.memberService;

import cn.tycoding.entity.Result;

public interface PayService {

    public Result payForOrder(String account,String idCode,double money,String bankAccount,String bankPassword);


}
