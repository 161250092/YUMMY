package cn.yummy.controller.memeberController;

import cn.yummy.entity.order.MemberSearchEntity;
import cn.yummy.entity.primitiveType.Result;

import cn.yummy.entity.order.Order;
import cn.yummy.service.memberService.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberOrdersController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/submitOrder")
    public Result submitOrder(@RequestBody Order order) {
//         System.out.println("订单项:"+order.getDishes().size());
//         System.out.println(order.getDishes().get(0).getSelectQuantity());
//         System.out.println("地址"+order.getUserLocation().getAddress());
//        return new Result(true, "80.8");
        return orderService.submitOrder(order);
    }

    @RequestMapping("/getMemberOrders")
    public List getMemberOrders(@RequestParam("account")String account){

        return orderService.getMemberOrders(account);
    }

    @RequestMapping("/searchMemberOrders")
    public List searchMemberOrders(@RequestBody MemberSearchEntity memberSearchEntity){
//商店名可能为空

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String account = (String)attributes.getRequest().getSession().getAttribute("account");
        memberSearchEntity.setAccount(account);
        return orderService.searchMemberOrders(memberSearchEntity);
    }






    @RequestMapping("/confirmOrder")
    public Result confirmOrder(@RequestParam("orderId")long orderId) {
        return orderService.confirmOrder(orderId);
//        return new Result(true,"已收到");
    }


    @RequestMapping("/abolishOrder")
    public Result abolishOrder(@RequestParam("orderId")long orderId) {
        return orderService.abolishOrder(orderId);
//        return new Result(true,"该订单已经废弃");
    }

    @RequestMapping("/cancelOrder")
    public Result cancelOrder(@RequestParam("orderId")long orderId) {
        return orderService.cancelOrder(orderId);

    }





}
