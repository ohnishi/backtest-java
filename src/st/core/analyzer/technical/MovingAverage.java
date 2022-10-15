package st.core.analyzer.technical;

import java.math.BigDecimal;

/**
 * このクラスは移動平均値に関連するデータを持ちます。
 */
public class MovingAverage {
    private int days;
    private BigDecimal movingAverage;

    protected MovingAverage(){
    	//同一パッケージからしかインスタンス化させない
    }


    /**
     * 集計日数を返します。
     * @return 集計日数
     */
    public int getDays() {
        return days;
    }
    /**
     * 移動平均値を返します。
     * @return 移動平均値
     */
    public BigDecimal getMovingAverage() {
        return movingAverage;
    }
    /**
     * 集計日数を設定します。
     * @param days 集計日数
     */
    protected void setDays(int days) {
        this.days = days;
    }
    /**
     * 移動平均値を設定します。
     * @param movingAverage 移動平均値
     */
    protected void setMovingAverage(BigDecimal movingAverage) {
        this.movingAverage = movingAverage;
    }
}
