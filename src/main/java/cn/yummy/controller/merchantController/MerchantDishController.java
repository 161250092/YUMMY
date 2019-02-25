package cn.yummy.controller.merchantController;

import cn.yummy.entity.primitiveType.PageBean;
import cn.yummy.entity.primitiveType.Result;
import cn.yummy.entity.merchant.Dish;
import cn.yummy.service.merchantService.DishManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/dishes")
public class MerchantDishController {

    @Autowired
    private DishManageService dishManageService;

    @RequestMapping("/findDishedByConPage")
    public PageBean findByConPage(
//            @RequestParam("idCode") String idCode,
            @RequestParam(value = "pageCode", required = false) int pageCode,
            @RequestParam(value = "pageSize", required = false) int pageSize) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String idCode =(String)attributes.getRequest().getSession().getAttribute("account");
        return dishManageService.findDishesByConPage(idCode,pageCode,pageSize);
    }


    @RequestMapping("delete")
    public Result deleteDish(@RequestParam("dishId") long dishId){
        if(dishManageService.deleteDish(dishId))
            return new Result(true,"删除成功");
        else
            return  new Result(false,"删除失败");
    }


    @RequestMapping("create")
    public Result createDish(@RequestBody Dish dish){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String idCode =(String)attributes.getRequest().getSession().getAttribute("account");
        dish.setIdCode(idCode);
        if(dishManageService.createDish(dish))
            return new Result(true,"创建成功");
        else
            return new Result(false,"创建失败");

    }

    @RequestMapping("update")
    public Result updateDish(@RequestBody Dish dish){
        if(dishManageService.updateDish(dish))
            return new Result(true,"修改成功");
        else
            return new Result(false,"修改失败");
    }




}
