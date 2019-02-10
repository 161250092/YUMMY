package cn.tycoding.service.Impl.merchant;

import cn.tycoding.dao.merchantDao.MerchantAccountDataService;
import cn.tycoding.entity.Result;
import cn.tycoding.entity.merchant.MerchantRegisterInf;
import cn.tycoding.service.merchantService.MerchantAccountService;
import cn.tycoding.util.GenerateIdCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MerchantAccountServiceImpl implements MerchantAccountService{

    @Autowired
    MerchantAccountDataService merchantAccountDataService;

    @Override
    public Result login(String idCode, String password) {
        String actualPassword = merchantAccountDataService.getPassword(idCode);
        if(actualPassword.equals(""))
            return new Result(false,"用户不存在");
        else if(!actualPassword.equals(password))
            return new Result(false,"用户名或密码错误");
        else {
            return new Result(true, "登陆成功");

        }
    }

    @Override
    public Result register(MerchantRegisterInf merchantRegisterInf) {
        String idCode = new GenerateIdCode().generateIdCode();
        merchantAccountDataService.createMerchantAccount(idCode,merchantRegisterInf);
        return new Result(true,idCode);
    }
}
