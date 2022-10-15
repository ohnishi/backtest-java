package st.core.analyzer.technical;

import java.math.BigDecimal;

/**
 * このクラスはＭＡＣＤに関連するデータを持ちます。
 */
public class MACD {

    public static final int DEFAULT_SHORT_DMA_DAYS = 12;
    public static final int DEFAULT_LONG_DMA_DAYS = 26;
    public static final int DEFAULT_SIGNAL_DAYS = 9;

    private MovingAverage shortMovingAverage;
    private MovingAverage longMovingAverage;
    private BigDecimal macd;
    private BigDecimal signal;
    private BigDecimal osci;

    protected MACD(){
    	//同一パッケージからしかインスタンス化させない
    }

    /**
     * macdを返します。
     * @return macd
     */
    public BigDecimal getMACD() {
        return macd;
    }
    /**
     * osciを返します。
     * @return osci
     */
    public BigDecimal getOSCI() {
        return osci;
    }
    /**
     * signalを返します。
     * @return signal
     */
    public BigDecimal getSignal() {
        return signal;
    }
    /**
     * macdを設定します。
     * @param macd
     */
    protected void setMACD(BigDecimal macd) {
        this.macd = macd;
    }
    /**
     * osciを設定します。
     * @param osci
     */
    protected void setOSCI(BigDecimal osci) {
        this.osci = osci;
    }
    /**
     * signalを設定します。
     * @param signal
     */
    protected void setSignal(BigDecimal signal) {
        this.signal = signal;
    }

    /**
     * 長期線の移動平均値を返します。
     * @return 長期線の移動平均値
     */
    public MovingAverage getLongMovingAverage() {
        return longMovingAverage;
    }
    /**
     * 長期線の移動平均値を設定します。
     * @param longMovingAverage 長期線の移動平均値
     */
    protected void setLongMovingAverage(MovingAverage longMovingAverage) {
        this.longMovingAverage = longMovingAverage;
    }
    /**
     * 短期線の移動平均値を返します。
     * @return 短期線の移動平均値
     */
    public MovingAverage getShortMovingAverage() {
        return shortMovingAverage;
    }
    /**
     * shortMovingAverageを設定します。
     * @param shortMovingAverage 短期線の移動平均値
     */
    protected void setShortMovingAverage(MovingAverage shortMovingAverage) {
        this.shortMovingAverage = shortMovingAverage;
    }
}
