package cn.yummy.dao.yummyDao;

import cn.yummy.dao.mysql.MySQLConnector;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
@Service
public class PaymentRecordDataServiceImpl implements PaymentRecordDataService {

        private Connection conn;


    @Override
    public void insertInRecord(long orderId, double bonus, String payer, String receiver) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "insert into paymentRecord(orderId,bonus,recordType,recordTime,payer,receiver)VALUES (?,?,?,?,?,?)";

            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,orderId);
            stmt.setDouble(2,bonus);
            stmt.setString(3,"in");
            stmt.setObject(4, LocalDateTime.now());
            stmt.setString(5,payer);
            stmt.setString(6,receiver);
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertOutRecord(long orderId, double bonus, String payer, String receiver) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "insert into paymentRecord(orderId,bonus,recordType,recordTime,payer,receiver)VALUES (?,?,?,?,?,?)";

            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,orderId);
            stmt.setDouble(2,bonus);
            stmt.setString(3,"out");
            stmt.setObject(4, LocalDateTime.now());
            stmt.setString(5,payer);
            stmt.setString(6,receiver);
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String getPayer(long orderId) {

        String payerAccount ="";
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "select payer from paymentRecord where orderId=? and recordType=?";

            stmt = conn.prepareStatement(sql);

            stmt.setLong(1,orderId);
            stmt.setString(2,"in");
            ResultSet rs =  stmt.executeQuery();
            while(rs.next()){
                payerAccount = rs.getString("payer");
            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return payerAccount;
    }

    @Override
    public String getReceiver(long orderId) {
        String receiverAccount ="";
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "select receiver from paymentRecord where orderId=? and recordType=?";

            stmt = conn.prepareStatement(sql);

            stmt.setLong(1,orderId);
            stmt.setString(2,"in");
            ResultSet rs =  stmt.executeQuery();
            while(rs.next()){
                receiverAccount = rs.getString("receiver");
            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return receiverAccount;

    }
}
