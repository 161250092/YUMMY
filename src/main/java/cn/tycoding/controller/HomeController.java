package cn.tycoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页控制器
 *
 * @auther TyCoding
 * @date 2018/9/28
 */
@Controller
public class HomeController {

    /**
     * 系统首页
     *
     * @return
     */
    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "home/index";
    }

    /**
     * 商品列表页
     *
     * @return
     */
    @GetMapping(value = {"/goods"})
    public String user() {
        return "site/goods";
    }


    @GetMapping(value = {"/member"})
    public String member() {
        return "memberPages/memberLogin";
    }

    @GetMapping(value = {"/memberRegister"})
    public String memberRegister() {
        return "memberPages/memberRegister";
    }

    @GetMapping(value = {"/memberMainPage"})
    public String visitMerchants() {
        return "memberPages/memberMainPage";
    }

    @GetMapping(value = {"/memberOrders"})
    public String memberOrders() {
        return "memberPages/memberOrders";
    }

    @GetMapping(value = {"/memberPersonPage"})
    public String memberPersonPage() {
        return "memberPages/memberPersonPage";
    }

    @GetMapping(value = {"/memberStatisticsPage"})
    public String memberStatisticsPage() {
        return "memberPages/memberStatisticsPage";
    }



    @GetMapping(value = {"/enterMerchant"})
    public String enterMerchant() {
        return "memberPages/enterMerchant";
    }


    @GetMapping(value = {"/merchant"})
    public String merchant() {
        return "merchantPages/merchantLogin";
    }


    @GetMapping(value = {"/merchantRegister"})
    public String merchantRegister() {
        return "merchantPages/merchantRegister";
    }


    @GetMapping(value = {"/merchantEditPage"})
    public String merchantEdit() {
        return "merchantPages/merchantEditPage";
    }



    @GetMapping(value = {"/merchantStatisticsPage"})
    public String merchantStatisticsPage() {
        return "merchantPages/merchantStatisticsPage";
    }


    @GetMapping(value = {"/merchantPersonPage"})
    public String merchantPersonPage() {
        return "merchantPages/merchantPersonPage";
    }


    @GetMapping(value = {"/merchantOrderRecords"})
    public String merchantOrderRecords() {
        return "merchantPages/OrderRecords";
    }


    @GetMapping(value = {"/manager"})
    public String managerLogin() {
        return "managerPages/managerLogin";
    }


    @GetMapping(value = {"/managerStatistics"})
    public String managerStatistics() {
        return "managerPages/managerStatisticsPage";
    }


    @GetMapping(value = {"/reviewApplications"})
    public String reviewApplications() {
        return "managerPages/reviewApplications";
    }




}
