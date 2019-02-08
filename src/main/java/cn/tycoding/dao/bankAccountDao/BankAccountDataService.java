package cn.tycoding.dao.bankAccountDao;

public interface BankAccountDataService {

    public void transferAccountIn(String account,double amount);

    public boolean transferAccountOut(String account,String password,double amount);

}
