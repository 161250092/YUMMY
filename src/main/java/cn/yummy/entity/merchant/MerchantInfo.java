package cn.yummy.entity.merchant;

import cn.yummy.entity.merchant.Dish;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MerchantInfo implements Serializable{

    private String idCode;
    //银行账号
    private String bankAccount;

    //商铺名
    private String restaurantName;

    //电话
    private String phone;

    private Location location;

    //餐馆类型
    private String restaurantType;


    //最低起送价格
    private double minDeliveryCost;

    //配送费
    private double  deliveryCost;

    //优惠 满多少减多少
    private List<Discount> discounts;

    private String infoDetails;

    public MerchantInfo() {

//        idCode="0000001";
//        bankAccount="123456";
//        restaurantName="西子醋鱼";
//        phone="12580";
//        location = new Location(3.333333,111.11111,"南京鼓楼珠江路1号");
//        restaurantType="中餐";
//        minDeliveryCost=20;
//        deliveryCost=2;
//        Discount discount1 = new Discount(1,"0000001",40,10);
//        Discount discount2 = new Discount(2,"0000001",80,20);
//        discounts = new ArrayList<Discount>();
//        discounts.add(discount1);
//        discounts.add(discount2);
//
//
//        this.infoToString();
    }

    public String getIdCode() {
        return idCode;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getPhone() {
        return phone;
    }



    public String getRestaurantType() {
        return restaurantType;
    }

    public double getMinDeliveryCost() {
        return minDeliveryCost;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }




    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }



    public void setMinDeliveryCost(double minDeliveryCost) {
        this.minDeliveryCost = minDeliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }


    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {

        return location;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }


    public String getInfoDetails() {
        return infoDetails;
    }

    public void setInfoDetails(String infoDetails) {
        this.infoDetails = infoDetails;
    }


    public void infoToString(){
        infoDetails = "";
        infoDetails +="账号: "+this.bankAccount+"  \n";
        infoDetails +="店铺名: "+this.restaurantName+"  \n";
        infoDetails +="地址: "+this.location.getAddress()+"  \n";
        infoDetails +="联系电话: "+this.getPhone()  +"  \n";
        infoDetails +="餐馆类型: "+this.getRestaurantType()  +"  \n";
        infoDetails +="配送费: "+this.getDeliveryCost()  +"  \n";
        infoDetails +="起送价: "+this.getMinDeliveryCost()  +"  \n";
    }
}
