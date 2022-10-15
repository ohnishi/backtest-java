package st.core.analyzer.technical;


/**
 * このクラスは平均足に関連するデータを持ちます。
 */
public class AverageStick {

	/**
	 * 始値
	 */
	private long open;
	/**
	 * 高値
	 */
	private long high;
	/**
	 * 安値
	 */
	private long low;
	/**
	 * 終値
	 */
	private long close;
	/**
	 * 日時
	 */
	private long date;
	/**
	 * 通貨ペアコード
	 */
	private String code;

	protected AverageStick(){
		//同一パッケージからしかインスタンス化させない
	}

	/**
	 * スケール(小数点以下の桁数)を返します
	 * @return スケール
	 */
	public int getScale(){
		return 0;
	}




	public long getOpen() {
		return open;
	}

	public void setOpen(long open) {
		this.open = open;
	}

	public long getClose() {
		return close;
	}

	public void setClose(long close) {
		this.close = close;
	}

	public long getHigh() {
		return high;
	}

	public void setHigh(long high) {
		this.high = high;
	}

	public long getLow() {
		return low;
	}

	public void setLow(long low) {
		this.low = low;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
