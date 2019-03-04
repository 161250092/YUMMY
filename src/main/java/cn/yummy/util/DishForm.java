package cn.yummy.util;

import cn.yummy.entity.member.DishForMember;
import cn.yummy.entity.merchant.Dish;

public class DishForm {

    public DishForMember dishToDishForMember(Dish dish){
            DishForMember dishForMember = new DishForMember();

            dishForMember.setQuantity(dish.getQuantity());
            dishForMember.setPrice(dish.getPrice());
            dishForMember.setName(dish.getName());
            dishForMember.setType(dish.getType());
            dishForMember.setStartTime(dish.getStartTime());
            dishForMember.setEndTime(dish.getEndTime());
            dishForMember.setDescription(dish.getDescription());
            dishForMember.setIdCode(dish.getIdCode());
            dishForMember.setDishId(dish.getDishId());
            dishForMember.setImage(dish.getImage());
            return dishForMember;


    }

}
