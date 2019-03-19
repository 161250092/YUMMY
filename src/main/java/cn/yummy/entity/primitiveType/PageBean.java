package cn.yummy.entity.primitiveType;

import java.io.Serializable;
import java.util.List;

/**
 * @auther MWX
 * @date 2019/1/28
 */
public class PageBean implements Serializable {

    //当前页
    private long total;
    //当前页记录
    private List rows;

    public PageBean(long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
