package cn.yummy.controller.managerController;

import cn.yummy.entity.Result;
import cn.yummy.service.managerService.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerApplicationsController {

    @Autowired
    private ApprovalService approvalService;

    @RequestMapping("/getApplications")
    public List getApplications(@RequestParam("account")String account){
        return approvalService.getAllApplication();
    }


    @RequestMapping("/passApplication")
    public Result passApplication(@RequestParam("applicationId")long applicationId ){
//        System.out.println(applicationId);
//        return new Result(true,"通过审核");
        return approvalService.passTheApplication(applicationId);

    }

    @RequestMapping("/refuseApplication")
    public Result refuseApplication(@RequestParam("applicationId")long applicationId){
//        System.out.println(applicationId);
//        return new Result(true,"驳回成功");
        return approvalService.refuseTheApplication(applicationId);
    }






}
