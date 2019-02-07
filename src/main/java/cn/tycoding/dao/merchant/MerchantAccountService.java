package cn.tycoding.dao.merchant;

import cn.tycoding.entity.merchant.MerchantInfo;
import cn.tycoding.entity.merchant.MerchantRegisterInf;

public interface MerchantAccountService {

    public int getMerchantNum();

    public boolean createMerchantAccount(String idCode, MerchantRegisterInf merchantRegisterInf);


    public String getPassword(String idCode);


}
