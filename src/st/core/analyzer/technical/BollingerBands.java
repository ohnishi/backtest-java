package st.core.analyzer.technical;

import java.math.BigDecimal;

/**
 * このクラスはボリンジャーバンドに関連するデータを持ちます。
 */
public class BollingerBands {

    public static final int DEFAULT_DAYS = 25;

    private MovingAverage ma;
    private BigDecimal p1σ;
    private BigDecimal p2σ;
    private BigDecimal p3σ;
    private BigDecimal m1σ;
    private BigDecimal m2σ;
    private BigDecimal m3σ;

    BollingerBands(){
    	//同一パッケージからしかインスタンス化させない
    }


	/**
	 * @return -1σバンド を返します。
	 */
	public BigDecimal getM1σ() {
		return m1σ;
	}
	/**
	 * @param m1σ -1σバンド を設定します。
	 */
	public void setM1σ(BigDecimal m1σ) {
		this.m1σ = m1σ;
	}
	/**
	 * @return -2σバンド を返します。
	 */
	public BigDecimal getM2σ() {
		return m2σ;
	}
	/**
	 * @param m2σ -2σバンド を設定します。
	 */
	public void setM2σ(BigDecimal m2σ) {
		this.m2σ = m2σ;
	}
	/**
	 * @return -3σバンド を返します。
	 */
	public BigDecimal getM3σ() {
		return m3σ;
	}
	/**
	 * @param m3σ -3σバンド を設定します。
	 */
	public void setM3σ(BigDecimal m3σ) {
		this.m3σ = m3σ;
	}
	/**
	 * @return 中央バンド を返します。
	 */
	public MovingAverage getMa() {
		return ma;
	}
	/**
	 * @param ma 中央バンド を設定します。
	 */
	public void setMa(MovingAverage ma) {
		this.ma = ma;
	}
	/**
	 * @return +1σバンド を返します。
	 */
	public BigDecimal getP1σ() {
		return p1σ;
	}
	/**
	 * @param p1σ +1σバンド を設定します。
	 */
	public void setP1σ(BigDecimal p1σ) {
		this.p1σ = p1σ;
	}
	/**
	 * @return +2σバンド を返します。
	 */
	public BigDecimal getP2σ() {
		return p2σ;
	}
	/**
	 * @param p2σ +2σバンド を設定します。
	 */
	public void setP2σ(BigDecimal p2σ) {
		this.p2σ = p2σ;
	}
	/**
	 * @return +3σバンド を返します。
	 */
	public BigDecimal getP3σ() {
		return p3σ;
	}
	/**
	 * @param p3σ +3σバンド を設定します。
	 */
	public void setP3σ(BigDecimal p3σ) {
		this.p3σ = p3σ;
	}
}
