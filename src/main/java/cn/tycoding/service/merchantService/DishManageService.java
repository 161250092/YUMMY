package cn.tycoding.service.merchantService;



import cn.tycoding.entity.PageBean;
import cn.tycoding.entity.Result;
import cn.tycoding.entity.merchant.Dish;

import java.util.List;

public interface DishManageService {


    public List getMerchantDish(String idCode);

    public List<Dish> getMerchantDishInForce(String idCode);

    public PageBean findDishesByConPage(String idCode, int pageCode, int pageSize);

    public boolean deleteDish(long dishId);

    public boolean createDish(Dish dish);

    public boolean updateDish(Dish dish);

}
