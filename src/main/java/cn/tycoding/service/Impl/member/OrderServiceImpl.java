package cn.tycoding.service.Impl.member;

import cn.tycoding.dao.bankAccountDao.BankAccountDataServiceImpl;
import cn.tycoding.dao.memberDao.MemberOrderDataService;
import cn.tycoding.dao.yummyDao.PaymentRecordDataService;
import cn.tycoding.entity.MemberSearchEntity;
import cn.tycoding.entity.Result;
import cn.tycoding.entity.order.Order;
import cn.tycoding.entity.order.OrderState;
import cn.tycoding.service.memberService.OrderService;
import cn.tycoding.util.ComputeArrivalTime;
import cn.tycoding.util.ComputePrice;
import cn.tycoding.util.OrderDelayedTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private MemberOrderDataService memberOrderDataService;


    @Autowired
    private BankAccountDataServiceImpl bankAccountDataService;

    @Autowired
    private PaymentRecordDataService paymentRecordDataService;

    private   ComputePrice computePrice;

    private ComputeArrivalTime computeArrivalTime;

    private OrderDelayedTransaction orderDelayedTransaction;

    @Override
    public Result submitOrder(Order order) {

        order.setOrderState(new OrderState(false,false,false));

//      compute price
        computePrice = new ComputePrice();
        double totalPrice = computePrice.computeTotalPrice(order);
        double priceWithoutDiscount = computePrice.computePriceWithoutDiscount(order);
        order.setTotalPrice(totalPrice);

        if(!computePrice.isReachMinDelivery(priceWithoutDiscount,order))
            return new Result(false,"总价低于起送价");


//      提交订单
        memberOrderDataService.submitOrder(order);

//        延时处理
        orderDelayedTransaction = new OrderDelayedTransaction();
        orderDelayedTransaction.delayedTransaction(order.getOrderId());

        String message = "总价: "+totalPrice+" 折扣:"+(priceWithoutDiscount-totalPrice);
        return new Result(true,message);

    }

    @Override
    public List getMemberOrders(String account) {
        return memberOrderDataService.getMemberOrders(account);
    }

    @Override
    public List searchMemberOrders(MemberSearchEntity memberSearchEntity) {
        return memberOrderDataService.searchMemberOrders(memberSearchEntity);
    }

    @Override
    public Result confirmOrder(long orderId) {
        if(memberOrderDataService.confirmOrder(orderId))
            return new Result(true,"确认接收");
        else
            return new Result(false,"接收失败");
    }

    @Override
    public Result abolishOrder(long orderId) {
        Order order = memberOrderDataService.getOrder(orderId);
//计算退款额
        double returnPrice = computePrice.returnMoney(order);

        String payer = paymentRecordDataService.getPayer(orderId);
        String receiver = paymentRecordDataService.getReceiver(orderId);
//平台和商家转出
        bankAccountDataService.AuthorizedTransferAccountOut(receiver,returnPrice*0.98);
        bankAccountDataService.AuthorizedTransferAccountOut("yummy",returnPrice*0.02);
//用户转入
        bankAccountDataService.transferAccountIn(payer,returnPrice);
//退款支出记录
        paymentRecordDataService.insertOutRecord(orderId,returnPrice*0.02,receiver,payer);

        if(memberOrderDataService.abolishOrder(orderId))
            return new Result(true,"退订成功,返回"+returnPrice+"元");
        else
            return new Result(false,"退订失败");
    }

    @Override
    public Result cancelOrder(long orderId) {
        if(memberOrderDataService.cancelOrder(orderId))
            return new Result(true,"撤销订单成功");
        else
            return new Result(false,"撤下失败");
    }


    @Override
    public void turnOrderStateIsPayed(Order order) {
        memberOrderDataService.turnOrderStateIsPayed(order);
    }


}
