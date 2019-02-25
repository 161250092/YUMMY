package cn.yummy.entity.merchant;

import java.io.Serializable;

public class MerchantRegisterInf implements Serializable {
    private String password;
    private String restaurantName;
    private String bankAccount;
    private String phone;

    public MerchantRegisterInf() {
    }

    public String getPassword() {
        return password;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
