package cn.tycoding.service.merchantService;


import cn.tycoding.entity.Result;
import cn.tycoding.entity.merchant.MerchantInfo;

import java.util.List;

public interface MerchantInformationService {

    public List<MerchantInfo> getAllMerchant();

    public MerchantInfo getMerchantInfo(String idCode);

    public boolean updateMerchantInfo(MerchantInfo merchantInfo );


    public boolean addNewDiscount(String idCode,double totalPrice,double reducePrice);


    public boolean deleteDiscount(long discountId);

}
