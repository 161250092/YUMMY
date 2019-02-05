package cn.tycoding.dao;

import cn.tycoding.entity.merchant.MerchantInfo;

public interface merchantService {

    public int getMerchantNum();

    public boolean createMerchantAccount(String idCode, String password, MerchantInfo merchantInfo);



}
