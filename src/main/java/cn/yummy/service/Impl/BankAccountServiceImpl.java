package cn.yummy.service.Impl;

import cn.yummy.dao.bankAccountDao.BankAccountDataService;
import cn.yummy.dao.memberDao.MemberInformationDataService;
import cn.yummy.dao.memberDao.MemberOrderDataService;
import cn.yummy.dao.memberDao.MemberStatisticsDataService;
import cn.yummy.dao.merchantDao.MerchantInformationDataService;
import cn.yummy.dao.yummyDao.PaymentRecordDataService;
import cn.yummy.entity.primitiveType.Result;
import cn.yummy.entity.order.Order;
import cn.yummy.entity.yummy.PayForm;
import cn.yummy.service.BankAccountService;
import cn.yummy.service.memberService.OrderService;
import cn.yummy.util.ComputeArrivalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private MemberOrderDataService memberOrderDataService;

    @Autowired
    private BankAccountDataService bankAccountDataService;

    @Autowired
    private MerchantInformationDataService merchantInformationDataService;

    @Autowired
    private PaymentRecordDataService paymentRecordDataService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberStatisticsDataService memberStatisticsDataService;

    @Autowired
    private MemberInformationDataService memberInformationDataService;


    private ComputeArrivalTime computeArrivalTime;


    @Override
    public Result payForOrder(PayForm payForm) {
        if(payForm.isDeliveryRightNow())
            payForm.setDeliveryTime(LocalDateTime.now());
        else
            payForm.formatTransfer();

        return out(payForm.getAccount(),payForm.getPassword(),payForm.getOrderId(),payForm.getIdCode(),payForm.getDeliveryTime());
    }

    @Override
    public Result out(String account, String password, long orderId,String idCode,LocalDateTime deliveryTime) {
        double totalPrice = memberOrderDataService.getOrderPrice(orderId);
//      验证账户、余额
        Result rs = this.validate(account,password,totalPrice);
        if(!rs.isSuccess())
            return rs;

//      转入的账号
        String bankAccount = merchantInformationDataService.getMerchantInfo(idCode).getBankAccount();
//      商家收入
        double merchantBonus = totalPrice*0.98;
//      yummy收入
        double bonus = totalPrice*0.02;

//      提交日期,送餐时间与预计时间
        Order order = memberOrderDataService.getOrder(orderId);
        computeArrivalTime = new ComputeArrivalTime();
        order.setSubmitTime(LocalDateTime.now());
        order.setDeliveryTime(deliveryTime);
        order.setExpectedArriveTime(computeArrivalTime.computeArrivalTime(order));

        if(!computeArrivalTime.accessible)
            return new Result(false,"超出配送范围");

//      用户将钱转出
        if(bankAccountDataService.transferAccountOut(account,password,totalPrice)&&in(bankAccount,merchantBonus).isSuccess()
                &&in("yummy",bonus).isSuccess()) {

//payment记录
            paymentRecordDataService.insertInRecord(orderId,bonus,account,bankAccount);
//订单状态修改为已支付，并修改提交时间与预测时间
            orderService.turnOrderStateIsPayed(order);
//更新用户等级
            updateMemberLevelThroughConsumption(memberOrderDataService.getOrder(orderId).getAccount());

            return new Result(true, "支付成功,预计"+order.getExpectedArriveTime().toString()+"送达");
        }
        else
            return new Result(false,"支付失败");
    }

    public Result validate(String account, String password,double totalPrice){
        if(!bankAccountDataService.login(account,password))
            return new Result(false,"用户名或密码错误");
        if(!bankAccountDataService.isBalanceEnough(account,totalPrice))
            return new Result(false,"余额不足");

            return new Result(true,"");
    }

    private void updateMemberLevelThroughConsumption(String account){
        double totalConsumption =  memberStatisticsDataService.getMemberConsumption(account);
        int newLevel = (int)totalConsumption/1000+1;
//        System.out.println(account+"总消费"+totalConsumption+" 等级: "+newLevel);
        memberInformationDataService.updateMemberLevel(account,newLevel);
    }


    @Override
    public Result in(String account, double pay) {
        if(bankAccountDataService.transferAccountIn(account,pay))
            return  new Result(true,"success");
        else
            return  new Result(false,"failure");
    }

    @Override
    public Result unsubscribeOut(String account, double amount) {

        boolean isSuccess = bankAccountDataService.AuthorizedTransferAccountOut(account,amount);
        if(isSuccess)
            return new Result(true,"退款成功");
        else
            return new Result(false,"退款失败");
    }


}
