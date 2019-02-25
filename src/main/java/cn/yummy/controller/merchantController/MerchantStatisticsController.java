package cn.yummy.controller.merchantController;


import cn.yummy.entity.merchant.MerchantStatistics;
import cn.yummy.service.merchantService.MerchantStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/merchant")
public class MerchantStatisticsController {

    @Autowired
    private MerchantStatisticsService merchantStatisticsService;

    @RequestMapping("/getMerchantStatistics")
    public MerchantStatistics getMerchantStatistics(@RequestParam("account")String account){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        account = (String)attributes.getRequest().getSession().getAttribute("account");
        return merchantStatisticsService.getMerchantStatistics(account);
    }

}
