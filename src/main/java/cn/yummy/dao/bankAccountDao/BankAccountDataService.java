package cn.yummy.dao.bankAccountDao;

public interface BankAccountDataService {

    public boolean login(String account,String password);

//    转入
    public boolean transferAccountIn(String account,double amount);
//    转出
    public boolean transferAccountOut(String account,String password,double amount);

//    用来退款
    public boolean AuthorizedTransferAccountOut(String account,double amount);

    public boolean isBalanceEnough(String account,double amount);


}
