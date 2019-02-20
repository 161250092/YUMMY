package cn.tycoding.dao.memberDao;

import cn.tycoding.dao.mysql.MySQLConnector;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Service
public class MemberStatisticsDataServiceImpl implements MemberStatisticsDataService {

    private Connection conn;
    @Override
    public double getMemberConsumption(String account) {

        double total = 0;
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "select sum(totalPrice)  as total from order_tb where account=?  and isPayed=? and isAbolished=? group by account";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,account);
            stmt.setBoolean(2,true);
            stmt.setBoolean(3,false);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                total = rs.getDouble("total");
            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return total;
    }

    @Override
    public int getAbolishedOrders(String account) {
        return 0;
    }

    @Override
    public int getAcceptedOrders(String account) {
        return 0;
    }

    @Override
    public List<String> getMerchantConsumed(String account) {
        return null;
    }

    @Override
    public List<Double> getMemberConsumptionInEachMerchant(String account) {
        return null;
    }
}
