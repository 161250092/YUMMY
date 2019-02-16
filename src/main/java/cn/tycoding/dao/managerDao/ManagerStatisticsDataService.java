package cn.tycoding.dao.managerDao;

import java.util.ArrayList;


public interface ManagerStatisticsDataService {
//  餐厅数量
    public int getRestaurantNum();

//  会员数量
    public int getMemberNum();

//  各个等级的会员数量
    public ArrayList<Integer> getEachLevelMemberNum();


//  月实际收入
    public double getMonthlyActualIncome();

//  月收入
    public double getMonthlyIncome();

//  月退款
    public double getMonthlyExpense();


//  账户余额
    public double getBalance();



}
