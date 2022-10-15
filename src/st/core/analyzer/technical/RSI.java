package st.core.analyzer.technical;

import java.math.BigDecimal;

/**
 * このクラスは相対力指数ＲＳＩに関連するデータを持ちます。
 */
public class RSI {

    public static final int DEFAULT_DAYS = 14;

    private int days;
    private BigDecimal rsi;

    protected RSI(){
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
     * RSIの値を返します。
     * @return RSIの値
     */
    public BigDecimal getRsi() {
        return rsi;
    }
    /**
     * 集計日数を設定します。
     * @param days 集計日数
     */
    protected void setDays(int days) {
        this.days = days;
    }
    /**
     * RSIの値を設定します。
     * @param rsi RSIの値
     */
    protected void setRsi(BigDecimal rsi) {
        this.rsi = rsi;
    }
}
