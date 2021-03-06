package cn.yummy.service.Impl.member;

import cn.yummy.dao.merchantDao.MerchantDishesDataService;
import cn.yummy.dao.merchantDao.MerchantInformationDataService;
import cn.yummy.entity.member.DishForMember;
import cn.yummy.entity.merchant.Dish;
import cn.yummy.entity.merchant.MerchantInfo;
import cn.yummy.service.memberService.MerchantVisitService;
import cn.yummy.util.DishForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantVisitServiceImpl implements MerchantVisitService{

    @Autowired
    private MerchantInformationDataService merchantInformationDataService;

    @Autowired
    private MerchantDishesDataService merchantDishesDataService;

    @Override
    public List getAllAccessibleMerchants() {
        return merchantInformationDataService.getAllAccessibleMerchants();
    }

    @Override
    public List getAllMerchants() {
        return merchantInformationDataService.getAllMerchant();
    }

    @Override
    public List searchMerchants(String restaurantName, String restaurantType) {
        return merchantInformationDataService.searchMerchants(restaurantName,restaurantType);
    }

    @Override
    public List getMerchantAllDishesInForce(String idCode) {
        DishForm dishForm = new DishForm();
        List<DishForMember> dishForMembers = new ArrayList();

        for(Dish dish:merchantDishesDataService.getMerchantDishesInForce(idCode)){
            dishForMembers.add(dishForm.dishToDishForMember(dish));
        }
        return dishForMembers;
    }

    @Override
    public MerchantInfo getMerchantInfo(String idCode) {
        return merchantInformationDataService.getMerchantInfo(idCode);
    }


}
