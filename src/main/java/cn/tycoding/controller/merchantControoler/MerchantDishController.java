package cn.tycoding.controller.merchantControoler;

import cn.tycoding.entity.PageBean;
import cn.tycoding.entity.Result;
import cn.tycoding.entity.merchant.Dish;
import cn.tycoding.entity.merchant.MerchantInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dishes")
public class MerchantDishController {


    @RequestMapping("/findDishedByConPage")
    public PageBean findByConPage(
            @RequestParam("idCode") String idCode,
            @RequestParam(value = "pageCode", required = false) int pageCode,
            @RequestParam(value = "pageSize", required = false) int pageSize) {

        List  rows = new ArrayList();
        for(int i=0;i<pageSize;i++){
            rows.add(new Dish(i,idCode));
        }
        PageBean pageBean =new PageBean(10*pageSize,rows);
        return pageBean;
    }


    @RequestMapping("delete")
    public Result deleteDish(@RequestParam("dishId") String dishId){
        return new Result(true,"删除成功");
    }


    @RequestMapping("create")
    public Result createDish(@RequestBody Dish dish){
        System.out.println(dish.getStartTime());
        return new Result(true,"创建成功");
    }

    @RequestMapping("update")
    public Result updateDish(@RequestBody Dish dish){
        System.out.println(dish.getStartTime());
        return new Result(true,"修改成功");
    }




}
