package cn.yummy.entity.merchant;


import java.io.Serializable;
import java.time.LocalDate;

public class Dish implements Serializable{

    private long dishId;

    private String idCode;

    private LocalDate  startTime;

    private LocalDate  endTime;

    private String type;

    private String image;

    private String name;

    private double price;

    //商家使用时是作为出售上限，会员使用时是作为选择数量
    private int quantity;

    private String description;

    public Dish() {
    }

    public Dish(long dishId, String idCode) {

        this.dishId = dishId;
        this.idCode = idCode;
        startTime = LocalDate.now();
        endTime = LocalDate.now();
        type="type";
        name="name";
        price=10;
        quantity=100;
        description="nothing";

    }

    public Dish(LocalDate startTime, LocalDate endTime, String type, String name, double price, int quantity, String description) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public long getDishId() {
        return dishId;
    }

    public String getIdCode() {
        return idCode;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public String getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setDishId(long dishId) {
        this.dishId = dishId;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
