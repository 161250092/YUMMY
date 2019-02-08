package cn.tycoding.dao.merchantDao;

import cn.tycoding.dao.mysql.MySQLConnector;
import cn.tycoding.entity.PageBean;
import cn.tycoding.entity.merchant.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MerchantDishesDataServiceImpl implements MerchantDishesDataService {

    private Connection conn;

    @Override
    public List getMerchantDish(String idCode) {

        List dishes = new ArrayList<Dish>();
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "select * from dish where idCode=?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,idCode);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
               Dish dish  = new Dish();
               dish.setDishId(rs.getLong("dishId"));
               dish.setIdCode(rs.getString("idCode"));
               dish.setEndTime(rs.getDate("endTime").toLocalDate());
               dish.setStartTime(rs.getDate("startTime").toLocalDate());
               dish.setType(rs.getString("type"));
               dish.setName(rs.getString("name"));
               dish.setPrice(rs.getDouble("price"));
               dish.setQuantity(rs.getInt("quantity"));
               dish.setDescription(rs.getString("description"));
               dishes.add(dish);
            }
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return dishes;
    }

    @Override
    public PageBean findDishesByConPage(String idCode, int pageCode, int pageSize) {

        List dishes = this.getMerchantDish(idCode);
        List rows = new ArrayList<Dish>();

        //一个页面，页面内容少于最大pageSize
        if(dishes.size()<=pageSize){
            rows  = dishes;
            return new PageBean(dishes.size(),rows);
        }

        for(int i=(pageCode-1)*pageSize;
            i<dishes.size()&&i<pageCode*pageSize;
            i++){
            rows.add(dishes.get(i));
        }

        return new PageBean(dishes.size(),rows);
    }

    @Override
    public boolean deleteDish(long dishId) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "delete from dish where dishId=?";

            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,dishId);

            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }




        return true;
    }

    @Override
    public boolean createDish(Dish dish) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "insert into dish(idCode,startTime,endTime,dishType,dishName,price,quantity,description)VALUES(?,?,?,?,?,?,?,?) ";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,dish.getIdCode());
            stmt.setDate(2,java.sql.Date.valueOf(dish.getStartTime()));
            stmt.setDate(3,java.sql.Date.valueOf(dish.getEndTime()));
            stmt.setString(4,dish.getType());
            stmt.setString(5,dish.getName());
            stmt.setDouble(6,dish.getPrice());
            stmt.setInt(7,dish.getQuantity());
            stmt.setString(8,dish.getDescription());
            stmt.executeUpdate();

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }



        return false;
    }

    @Override
    public boolean updateDish(Dish dish) {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "update dish set startTime=?,endTime=?,dishType=?,dishName=?, price=?,quantity=?,description=?" +
                    "where dishId=?";

            stmt = conn.prepareStatement(sql);
            stmt.setDate(1,java.sql.Date.valueOf(dish.getStartTime()));
            stmt.setDate(2,java.sql.Date.valueOf(dish.getEndTime()));
            stmt.setString(3,dish.getType());
            stmt.setString(4,dish.getName());
            stmt.setDouble(5,dish.getPrice());
            stmt.setInt(6,dish.getQuantity());
            stmt.setString(7,dish.getDescription());
            stmt.setLong(8,dish.getDishId());

            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }


        return false;
    }



}
