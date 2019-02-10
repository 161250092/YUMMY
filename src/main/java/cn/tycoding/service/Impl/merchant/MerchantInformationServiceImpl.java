package cn.tycoding.service.Impl.merchant;

import cn.tycoding.dao.merchantDao.MerchantInformationDataService;
import cn.tycoding.entity.merchant.MerchantInfo;
import cn.tycoding.service.merchantService.MerchantInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantInformationServiceImpl implements MerchantInformationService{

    @Autowired
    private MerchantInformationDataService merchantInformationDataService;

    @Override
    public List<MerchantInfo> getAllMerchant() {
        return merchantInformationDataService.getAllMerchant();
    }

    @Override
    public MerchantInfo getMerchantInfo(String idCode) {
        return merchantInformationDataService.getMerchantInfo(idCode);
    }

    @Override
    public boolean updateMerchantInfo(MerchantInfo merchantInfo) {
        return  merchantInformationDataService.updateMerchantInfo(merchantInfo);
    }

    @Override
    public boolean addNewDiscount(String idCode, double totalPrice, double reducePrice) {
        return merchantInformationDataService.addNewDiscount(idCode,totalPrice,reducePrice);
    }

    @Override
    public boolean deleteDiscount(long discountId) {
        return merchantInformationDataService.deleteDiscount(discountId);
    }
}
