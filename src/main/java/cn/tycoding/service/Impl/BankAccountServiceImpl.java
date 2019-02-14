package cn.tycoding.service.Impl;

import cn.tycoding.dao.bankAccountDao.BankAccountDataService;
import cn.tycoding.dao.memberDao.MemberOrderDataService;
import cn.tycoding.dao.merchantDao.MerchantInformationDataService;
import cn.tycoding.entity.Result;
import cn.tycoding.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private MemberOrderDataService memberOrderDataService;

    @Autowired
    private BankAccountDataService bankAccountDataService;

    @Autowired
    private MerchantInformationDataService merchantInformationDataService;

    @Override
    public Result out(String account, String password, long orderId,String idCode) {
        double totalPrice = memberOrderDataService.getOrderPrice(orderId);

        if(!bankAccountDataService.login(account,password))
            return new Result(false,"用户名或密码错误");
        if(!bankAccountDataService.isBalanceEnough(account,totalPrice))
            return new Result(false,"余额不足");

        String bankAccount = merchantInformationDataService.getMerchantInfo(idCode).getBankAccount();

        if(bankAccountDataService.transferAccountOut(account,password,totalPrice)&&in(bankAccount,totalPrice).isSuccess())
            return new Result(true,"转出成功");
        else
            return new Result(false,"转出失败");

    }



    @Override
    public Result in(String account, double pay) {
        if(bankAccountDataService.transferAccountIn(account,pay))
            return  new Result(true,"success");
        else
            return  new Result(false,"failure");
    }


}
