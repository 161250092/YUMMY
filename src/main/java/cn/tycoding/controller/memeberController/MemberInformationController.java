package cn.tycoding.controller.memeberController;

import cn.tycoding.entity.PageBean;
import cn.tycoding.entity.Result;
import cn.tycoding.entity.member.Member;
import cn.tycoding.entity.merchant.Location;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberInformationController {

    @RequestMapping("/getMemberLocations")
    public PageBean findByConPage(
            @RequestParam("account") String account) {
            System.out.println("account: "+account);
        List rows = new ArrayList();
        for(int i=0;i<3;i++){
            rows.add(new Location(i));
        }
        PageBean pageBean =new PageBean(3,rows);
        return pageBean;
    }

    @RequestMapping("/getMemberInformation")
    public Member getMemberInformation(@RequestParam("account") String account){
        System.out.println(account);
        return new Member(account);
    }

    @RequestMapping("/updateMemberInformation")
    public Result updateMemberInformation(@RequestBody Member member){
        System.out.println(member.getNickName());
        return new Result(true,"更新成功");
    }



    @RequestMapping("/addNewLocation")
    public Result addNewLocation(@RequestBody Location location){
        System.out.println(location.getAddress());
        return new Result(true,"添加成功");
    }



    @RequestMapping("/deleteLocation")
    public Result deleteLocation(@RequestParam("locationId")long locationId){
        System.out.println(locationId);
        return new Result(true,"删除成功");
    }



    @RequestMapping("/deleteAccount")
    public Result deleteAccount(@RequestParam("account")String account){
        System.out.println(account);
        return new Result(true,"账号注销成功");
    }


}
