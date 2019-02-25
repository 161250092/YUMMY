package cn.yummy.service.merchantService;


import cn.yummy.entity.primitiveType.Result;
import cn.yummy.entity.merchant.MerchantRegisterInf;

public interface MerchantAccountService {

    public Result login(String idCode, String password);

    //message里是idCode
    public Result register(MerchantRegisterInf merchantRegisterInf);





}
