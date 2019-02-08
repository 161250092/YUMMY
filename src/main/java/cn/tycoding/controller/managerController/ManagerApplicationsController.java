package cn.tycoding.controller.managerController;

import cn.tycoding.entity.Result;
import cn.tycoding.entity.manager.ApplicationFromMerchant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerApplicationsController {

    @RequestMapping("/getApplications")
    public List getApplications(@RequestParam("account")String account){
        System.out.println(account);
        List<ApplicationFromMerchant> applications = new ArrayList<>();

        for(int i=0;i<10;i++){
            applications.add(new ApplicationFromMerchant());
        }
        return applications;
    }


    @RequestMapping("/passApplication")
    public Result passApplication(@RequestParam("applicationId")long applicationId ){
        System.out.println(applicationId);
        return new Result(true,"通过审核");
    }

    @RequestMapping("/refuseApplication")
    public Result refuseApplication(@RequestParam("applicationId")long applicationId){
        System.out.println(applicationId);
        return new Result(true,"驳回成功");
    }






}
