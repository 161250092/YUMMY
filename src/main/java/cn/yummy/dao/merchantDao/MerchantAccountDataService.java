package cn.yummy.dao.merchantDao;

import cn.yummy.entity.merchant.MerchantRegisterInf;

public interface MerchantAccountDataService {

    public int getMerchantNum();

    public boolean createMerchantAccount(String idCode, MerchantRegisterInf merchantRegisterInf);


    public String getPassword(String idCode);


}
