package st.core;

public class Price {

	/** 銘柄コード */
	private String code;
	/** 日付 */
	private long date;
	/** 始値 */
	private long open;
	/** 高値 */
	private long high;
	/** 安値 */
	private long low;
	/** 終値 */
	private long close;
	/** 出来高 */
	private long dealings;



	public long getClose() {
		return close;
	}
	public void setClose(long close) {
		this.close = close;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public long getDealings() {
		return dealings;
	}
	public void setDealings(long dealings) {
		this.dealings = dealings;
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
	public long getOpen() {
		return open;
	}
	public void setOpen(long open) {
		this.open = open;
	}
	public int getScale() {
		return 0;
	}
}
