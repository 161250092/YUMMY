package cn.tycoding.service.merchantService;


import cn.tycoding.entity.Result;

public interface MerchantInformationService {

    public Result modifyBankAccount(String idCode,String newBankAccount);

    public Result modifyRestaurantName(String idCode, String newName);

    public Result modifyPhone(String idCode, String newPhone);

    public Result modifyLocation(String idCode, String newLocation);

    public Result modifyLocationRestaurantType(String idCode, String newType);

    public Result modifyMinDeliveryCost(String idCode, double newMinDeliveryCost);

    public Result modifyDeliveryCost(String idCode, double newDeliveryCost);

}
