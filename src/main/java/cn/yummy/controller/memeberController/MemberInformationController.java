package cn.yummy.controller.memeberController;

import cn.yummy.entity.primitiveType.Result;
import cn.yummy.entity.member.Member;
import cn.yummy.entity.primitiveType.Location;
import cn.yummy.service.memberService.MemberInformationService;
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
public class MemberInformationController {

    @Autowired
    private MemberInformationService memberInformationService;

    @RequestMapping("/getMemberLocations")
    public List findByConPage(
            @RequestParam("account") String account) {
        return memberInformationService.getMemberInformation(account).getLocations();
    }

    @RequestMapping("/getMemberInformation")
    public Member getMemberInformation(@RequestParam("account") String account){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        account = (String) attributes.getRequest().getSession().getAttribute("account");
        return memberInformationService.getMemberInformation(account);
    }

    @RequestMapping("/updateMemberInformation")
    public Result updateMemberInformation(@RequestBody Member member){

        return memberInformationService.updateMemberInformation(member);
    }



    @RequestMapping("/addNewLocation")
    public Result addNewLocation(@RequestBody Location location){
//        System.out.println(location.getAddress());
//        return new Result(true,"添加成功");
//        System.out.println(location.getLat());
        if(location.getLat()==0)
            return new Result(false,"添加失败");
        return memberInformationService.addNewLocation(location);
    }



    @RequestMapping("/deleteLocation")
    public Result deleteLocation(@RequestParam("locationId")long locationId){
//        System.out.println(locationId);
//        return new Result(true,"删除成功");
        return memberInformationService.deleteLocation(locationId);
    }



    @RequestMapping("/deleteAccount")
    public Result deleteAccount(@RequestParam("account")String account){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        account = (String) attributes.getRequest().getSession().getAttribute("account");
//        return new Result(true,"账号注销成功");
        return memberInformationService.deleteAccount(account);
    }


}
