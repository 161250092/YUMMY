package cn.yummy.entity.primitiveType;


import java.io.Serializable;


public class Result implements Serializable {

    //判断结果
    private boolean success;
    //返回信息
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
