package cn.yummy.entity.merchant;

public class MarketStatistics {

    private double IncomeGenerationIndex;

    private double marketShare;

    public MarketStatistics(double incomeGenerationIndex, double marketShare) {
        IncomeGenerationIndex = incomeGenerationIndex;
        this.marketShare = marketShare;
    }


    public double getIncomeGenerationIndex() {
        return IncomeGenerationIndex;
    }

    public void setIncomeGenerationIndex(double incomeGenerationIndex) {
        IncomeGenerationIndex = incomeGenerationIndex;
    }

    public double getMarketShare() {
        return marketShare;
    }

    public void setMarketShare(double marketShare) {
        this.marketShare = marketShare;
    }


}
