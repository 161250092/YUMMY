package cn.yummy.controller.merchantController;

import cn.yummy.entity.order.MerchantSearchEntity;
import cn.yummy.service.merchantService.MerchantOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@RestController
@RequestMapping("/merchantOrders")
public class MerchantOrdersController {

    @Autowired
    private MerchantOrdersService merchantOrdersService;

    @RequestMapping("/getOrders")
    public List getAllOrders(@RequestParam("idCode") String idCode){
        return merchantOrdersService.getMerchantAllOrders(idCode);

    }

    @RequestMapping("/checkMerchantOrders")
    public List checkOrders(@RequestBody MerchantSearchEntity searchEntity){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String idCode = (String)attributes.getRequest().getSession().getAttribute("account");
        searchEntity.setIdCode(idCode);

        return merchantOrdersService.checkMerchantOrders(searchEntity);

    }

}
