package cn.yummy.dao.merchantDao;

import cn.yummy.entity.primitiveType.PageBean;
import cn.yummy.entity.merchant.Dish;

import java.util.List;

public interface MerchantDishesDataService {
    public List<Dish> getMerchantDish(String idCode);

    public List<Dish> getMerchantDishesInForce(String idCode);

    public PageBean findDishesByConPage(String idCode, int pageCode,int pageSize);

    public boolean deleteDish(long dishId);

    public boolean createDish(Dish dish);

    public boolean updateDish(Dish dish);

}
