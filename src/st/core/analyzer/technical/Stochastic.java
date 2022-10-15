package st.core.analyzer.technical;

import java.math.BigDecimal;

/**
 * このクラスはストキャスティクスに関連するデータを持ちます。
 */
public class Stochastic {

    public static final int DEFAULT_DAYS = 9;

    private int days;
    private BigDecimal k;
    private BigDecimal d;
    private BigDecimal slowD;

    protected Stochastic(){
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
     * 集計日数を設定します。
     * @param days 集計日数
     */
    void setDays(int days) {
        this.days = days;
    }

    /**
     * %Dを返します
     * @return %D
     */
	public BigDecimal getD() {
		return d;
	}
	/**
	 * %Dを設定します
	 * @param d %D
	 */
	void setD(BigDecimal d) {
		this.d = d;
	}

	/**
	 * %Kを返します
	 * @return %K
	 */
	public BigDecimal getK() {
		return k;
	}
	/**
	 * %Kを設定します
	 * @param k %K
	 */
	void setK(BigDecimal k) {
		this.k = k;
	}

	/**
	 * SLOW%Dを返します
	 * @return SLOW%D
	 */
	public BigDecimal getSlowD() {
		return slowD;
	}
	/**
	 * SLOW%Dを設定します
	 * @param slowD SLOW%D
	 */
	void setSlowD(BigDecimal slowD) {
		this.slowD = slowD;
	}


}
