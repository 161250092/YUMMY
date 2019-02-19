package cn.tycoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 页面跳转
 *
 * @auther MWX
 * @date 2019/1/28
 */
@Controller
public class HomeController {

    /**
     * index
     *
     * @return
     */
    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "home/index";
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

    @GetMapping(value = {"/memberLogout"})
    public String memberLogout() {
        return "memberPages/memberLogin";
    }

    @GetMapping(value = {"/mapLocation"})
    public String memberMapLocation() {
        return "memberPages/mapLocation";
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


    @GetMapping(value = {"/merchantLogout"})
    public String merchantLogout(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        attributes.getRequest().getSession().removeAttribute("account");
        return "merchantPages/merchantLogin";
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
