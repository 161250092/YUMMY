package cn.tycoding.service.Impl.member;

import cn.tycoding.dao.merchantDao.MerchantDishesDataService;
import cn.tycoding.dao.merchantDao.MerchantInformationDataService;
import cn.tycoding.entity.member.DishForMember;
import cn.tycoding.entity.merchant.Dish;
import cn.tycoding.entity.merchant.MerchantInfo;
import cn.tycoding.service.memberService.MerchantVisitService;
import cn.tycoding.util.DishForm;
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
