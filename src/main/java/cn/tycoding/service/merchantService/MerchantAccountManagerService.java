package cn.tycoding.service.merchantService;


import cn.tycoding.entity.Result;

public interface MerchantAccountManagerService {

    public Result login(String idCode, String password);

    //message里是idCode
    public Result register(String password, String verificationCode);





}
