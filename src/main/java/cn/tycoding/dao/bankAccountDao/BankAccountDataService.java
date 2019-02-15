package cn.tycoding.dao.bankAccountDao;

public interface BankAccountDataService {

    public boolean login(String account,String password);

    public boolean transferAccountIn(String account,double amount);

    public boolean transferAccountOut(String account,String password,double amount);

    public boolean AuthorizedTransferAccountOut(String account,double amount);

    public boolean isBalanceEnough(String account,double amount);


}
