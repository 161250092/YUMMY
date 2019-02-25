package cn.yummy.util;

import cn.yummy.dao.merchantDao.MerchantAccountDataService;
import cn.yummy.dao.merchantDao.MerchantAccountDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class GenerateIdCode {


    private MerchantAccountDataService merchantAccountDataService = new MerchantAccountDataServiceImpl();

    public String  generateIdCode(){

        int num = merchantAccountDataService.getMerchantNum();
        StringBuilder idCode = new StringBuilder(num + "");
        int extraLength = 7-idCode.length();
        for(int i=0;i<extraLength;i++){
            idCode.insert(0, "0");
        }
        return idCode.toString();
    }

}
