package cn.tycoding.dao.yummyDao;

public interface PaymentRecordDataService {
//     收入记录
    public void insertInRecord(long orderId,double bonus,String payer,String receiver);

//     退款记录
    public void insertOutRecord(long orderId,double bonus,String payer,String receiver);

    public  String getPayer(long orderId);

    public String getReceiver(long orderId);
}
