package cn.tycoding.entity.merchant;

public class MerchantStatistics {
    private double monthlyIncome;
    private int receivedOrdersNum;
    private int abolishedOrdersNum;

    public MerchantStatistics() {
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public int getReceivedOrdersNum() {
        return receivedOrdersNum;
    }

    public void setReceivedOrdersNum(int receivedOrdersNum) {
        this.receivedOrdersNum = receivedOrdersNum;
    }

    public int getAbolishedOrdersNum() {
        return abolishedOrdersNum;
    }

    public void setAbolishedOrdersNum(int abolishedOrdersNum) {
        this.abolishedOrdersNum = abolishedOrdersNum;
    }


}
