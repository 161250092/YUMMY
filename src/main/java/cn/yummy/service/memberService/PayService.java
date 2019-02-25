package cn.yummy.service.memberService;

import cn.yummy.entity.primitiveType.Result;

public interface PayService {

    public Result payForOrder(String account,String idCode,double money,String bankAccount,String bankPassword);


}
