package st.core.analyzer.technical;

import java.util.Date;

/**
 * 窓の情報クラス
 */
public class Mado {

	//窓の上限
	private long high;
	//窓の下限
	private long low;
	//窓を空けた日付
	private long day;


	//コンストラクタ
    protected Mado( long day, long val1, long val2 ){
    	//同一パッケージからしかインスタンス化させない
		this.day = day;
		this.high = Math.max(val1, val2);
		this.low = Math.min(val1, val2);
	}

	public Date getDay() {
		return new Date(day);
	}

	protected void setDay(Date day) {
		this.day = day.getTime();
	}

	public long getHigh() {
		return high;
	}

	protected void setHigh(long high) {
		this.high = high;
	}

	public long getLow() {
		return low;
	}

	protected void setLow(long low) {
		this.low = low;
	}

}
