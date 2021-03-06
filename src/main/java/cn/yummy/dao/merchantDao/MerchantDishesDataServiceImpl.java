package cn.yummy.dao.merchantDao;

import cn.yummy.dao.mysql.MySQLConnector;
import cn.yummy.entity.primitiveType.PageBean;
import cn.yummy.entity.merchant.Dish;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class MerchantDishesDataServiceImpl implements MerchantDishesDataService {

    private Connection conn;

    @Override
    public List<Dish> getMerchantDish(String idCode) {

        List dishes = new ArrayList<Dish>();
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "select * from dish where idCode=? and isAbolished=false";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,idCode);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
               Dish dish  = new Dish();
               dish.setDishId(rs.getLong("dishId"));
               dish.setIdCode(rs.getString("idCode"));
               dish.setEndTime(rs.getDate("endTime").toLocalDate());
               dish.setStartTime(rs.getDate("startTime").toLocalDate());
               dish.setType(rs.getString("dishType"));
               dish.setName(rs.getString("dishName"));
               dish.setPrice(rs.getDouble("price"));
               dish.setQuantity(rs.getInt("quantity"));
               dish.setDescription(rs.getString("description"));
               dish.setImage(rs.getString("img"));
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
    public List<Dish> getMerchantDishesInForce(String idCode) {
        List dishes = new ArrayList<Dish>();
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "select * from dish where idCode=? and ? between startTime and endTime and isAbolished=false";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,idCode);
            stmt.setObject(2, LocalDate.now());

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Dish dish  = new Dish();
                dish.setDishId(rs.getLong("dishId"));
                dish.setIdCode(rs.getString("idCode"));
                dish.setEndTime(rs.getDate("endTime").toLocalDate());
                dish.setStartTime(rs.getDate("startTime").toLocalDate());
                dish.setType(rs.getString("dishType"));
                dish.setName(rs.getString("dishName"));
                dish.setPrice(rs.getDouble("price"));
                dish.setQuantity(rs.getInt("quantity"));
                dish.setDescription(rs.getString("description"));
                dish.setImage(rs.getString("img"));
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
            sql = "update dish set isAbolished=true where dishId=?";

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
            sql = "insert into dish(idCode,startTime,endTime,dishType,dishName,price,quantity,description,img,isAbolished)VALUES(?,?,?,?,?,?,?,?,?,false) ";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,dish.getIdCode());
            stmt.setDate(2,java.sql.Date.valueOf(dish.getStartTime()));
            stmt.setDate(3,java.sql.Date.valueOf(dish.getEndTime()));
            stmt.setString(4,dish.getType());
            stmt.setString(5,dish.getName());
            stmt.setDouble(6,dish.getPrice());
            stmt.setInt(7,dish.getQuantity());
            stmt.setString(8,dish.getDescription());
            stmt.setString(9,dish.getImage());
            stmt.executeUpdate();

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }


        return true;
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


        return true;
    }



}
