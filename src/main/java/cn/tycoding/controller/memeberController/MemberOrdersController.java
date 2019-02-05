package cn.tycoding.controller.memeberController;

import cn.tycoding.entity.Result;
import cn.tycoding.entity.member.Member;
import cn.tycoding.entity.order.Order;
import cn.tycoding.entity.order.OrderState;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberOrdersController {


    @RequestMapping("/submitOrder")
    public Result submitOrder(@RequestBody Order order) {
         System.out.println("订单项:"+order.getDishes().size());
         System.out.println("地址"+order.getUserLocation().getAddress());
        return new Result(true, "80.8");
    }

    @RequestMapping("/getMemberOrders")
    public List getMemberOrders(@RequestParam("account")String account){
        System.out.println(account);

        List<Order> orders = new ArrayList<Order>();
        for(int i=0;i<10;i++){
            orders.add(new Order(i+1,"0000001",new OrderState(false,false,false)));
        }

        return orders;
    }


    @RequestMapping("/confirmOrder")
    public Result confirmOrder(@RequestParam("orderId")String orderId) {
        return new Result(true,"已收到");
    }


    @RequestMapping("/abolishOrder")
    public Result abolishOrder(@RequestParam("orderId")String orderId) {
        return new Result(true,"该订单已经废弃");
    }

    @RequestMapping("/cancelOrder")
    public Result cancelOrder(@RequestParam("orderId")String orderId) {
        return new Result(true,"该订单已经撤销");
    }





}
