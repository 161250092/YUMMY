package cn.tycoding.entity.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StatisticsInformation implements Serializable {

    private int restaurantNum;

    private int memberNum;

    private ArrayList<Integer> eachLevelMemberNum;

    private double monthlyActualIncome;

    private double monthlyIncome;

    private double monthlyExpense;

    private double balance;

    public StatisticsInformation() {
    }


    public int getRestaurantNum() {
        return restaurantNum;
    }

    public void setRestaurantNum(int restaurantNum) {
        this.restaurantNum = restaurantNum;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public List getEachLevelMemberNum() {
        return eachLevelMemberNum;
    }

    public void setEachLevelMemberNum(ArrayList eachLevelMemberNum) {
        this.eachLevelMemberNum = eachLevelMemberNum;
    }

    public double getMonthlyActualIncome() {
        return monthlyActualIncome;
    }

    public void setMonthlyActualIncome(double monthlyActualIncome) {
        this.monthlyActualIncome = monthlyActualIncome;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public double getMonthlyExpense() {
        return monthlyExpense;
    }

    public void setMonthlyExpense(double monthlyExpense) {
        this.monthlyExpense = monthlyExpense;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
