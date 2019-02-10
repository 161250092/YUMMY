package cn.tycoding.controller.merchantControoler;

import cn.tycoding.entity.PageBean;
import cn.tycoding.entity.SearchEntity;
import cn.tycoding.entity.merchant.Dish;
import cn.tycoding.entity.order.Order;
import cn.tycoding.entity.order.OrderState;
import cn.tycoding.service.merchantService.MerchantOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/merchantOrders")
public class MerchantOrdersController {

    @Autowired
    private MerchantOrdersService merchantOrdersService;

    @RequestMapping("/getOrders")
    public List getAllOrders(@RequestParam("idCode") String idCode){
//        List rows = new ArrayList();
//        for(int i=0;i<10;i++){
//            rows.add(new Order(i+1,"0000001",new OrderState(true,true,true)));
//        }
//
//        return rows;
        return merchantOrdersService.getMerchantAllOrders(idCode);

    }

    @RequestMapping("/checkMerchantOrders")
    public List checkOrders(@RequestBody SearchEntity searchEntity){
//        System.out.println(searchEntity.getHighPrice());
//        List rows = new ArrayList();
//        for(int i=0;i<10;i++){
//            rows.add(new Order(i+1,"0000001",new OrderState(true,true,true)));
//        }
//
//        return rows;
        return merchantOrdersService.checkMerchantOrders(searchEntity);

    }

}
