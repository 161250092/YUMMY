package cn.tycoding.service.merchantService;



import cn.tycoding.entity.Result;
import cn.tycoding.entity.merchant.Dish;

import java.util.List;

public interface DishManagerService {
    public Result createDish(Dish dish);

    public Result deleteDish(Dish dish);

    public Result editDish(Dish dish);

    public List<Dish> getAllDish(String idCode);

}
