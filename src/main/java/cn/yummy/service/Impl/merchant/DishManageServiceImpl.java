package cn.yummy.service.Impl.merchant;

import cn.yummy.dao.merchantDao.MerchantDishesDataService;
import cn.yummy.entity.primitiveType.PageBean;
import cn.yummy.entity.merchant.Dish;
import cn.yummy.service.merchantService.DishManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishManageServiceImpl implements DishManageService{
    @Autowired
    private MerchantDishesDataService merchantDishesDataService;

    @Override
    public List getMerchantDish(String idCode) {
        return merchantDishesDataService.getMerchantDish(idCode);
    }

    @Override
    public List<Dish> getMerchantDishInForce(String idCode) {
        return merchantDishesDataService.getMerchantDishesInForce(idCode);
    }

    @Override
    public PageBean findDishesByConPage(String idCode, int pageCode, int pageSize) {
        return merchantDishesDataService.findDishesByConPage(idCode,pageCode,pageSize);
    }

    @Override
    public boolean deleteDish(long dishId) {
        return merchantDishesDataService.deleteDish(dishId);
    }

    @Override
    public boolean createDish(Dish dish) {
        return merchantDishesDataService.createDish(dish);
    }

    @Override
    public boolean updateDish(Dish dish) {
        return merchantDishesDataService.updateDish(dish);
    }
}
