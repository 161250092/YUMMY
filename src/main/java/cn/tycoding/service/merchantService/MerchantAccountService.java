package cn.tycoding.service.merchantService;


import cn.tycoding.entity.Result;
import cn.tycoding.entity.merchant.MerchantRegisterInf;

public interface MerchantAccountService {

    public Result login(String idCode, String password);

    //message里是idCode
    public Result register(MerchantRegisterInf merchantRegisterInf);





}
