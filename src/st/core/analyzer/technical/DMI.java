package st.core.analyzer.technical;

import java.math.BigDecimal;

/**
 * このクラスはDMIに関連するデータを持ちます。
 * MM法に基づきADXは集計日数を１日にしています。
 * つまりADX == DXとなっています
 */
public class DMI {

    public static final int DEFAULT_DAYS = 14;

	private BigDecimal pdi;
	private BigDecimal mdi;
	private BigDecimal adx;
    private int days;

    protected DMI(){
    	//同一パッケージからしかインスタンス化させない
    }



	/**
	 * @return ADX を返します。
	 */
	public BigDecimal getAdx() {
		return adx;
	}
	/**
	 * @param adx ADX を設定します。
	 */
	public void setAdx(BigDecimal adx) {
		this.adx = adx;
	}
	/**
	 * @return 集計日数 を返します。
	 */
	public int getDays() {
		return days;
	}
	/**
	 * @param days 集計日数 を設定します。
	 */
	public void setDays(int days) {
		this.days = days;
	}
	/**
	 * @return -DI を返します。
	 */
	public BigDecimal getMdi() {
		return mdi;
	}
	/**
	 * @param mdi -DI を設定します。
	 */
	public void setMdi(BigDecimal mdi) {
		this.mdi = mdi;
	}
	/**
	 * @return +DI を返します。
	 */
	public BigDecimal getPdi() {
		return pdi;
	}
	/**
	 * @param pdi +DI を設定します。
	 */
	public void setPdi(BigDecimal pdi) {
		this.pdi = pdi;
	}
}
