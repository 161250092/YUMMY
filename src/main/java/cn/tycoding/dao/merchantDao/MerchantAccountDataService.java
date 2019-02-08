package cn.tycoding.dao.merchantDao;

import cn.tycoding.entity.merchant.MerchantRegisterInf;

public interface MerchantAccountDataService {

    public int getMerchantNum();

    public boolean createMerchantAccount(String idCode, MerchantRegisterInf merchantRegisterInf);


    public String getPassword(String idCode);


}
