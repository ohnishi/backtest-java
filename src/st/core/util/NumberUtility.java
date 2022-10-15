package st.core.util;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;


public class NumberUtility {

	public static int stringToInt(String value) {

		if( StringUtils.isBlank(value) ){
			return 0;
		}

		String numStr = StringUtils.replace(value, ",", "");

		int ret = 0;
		try{
			ret = Integer.parseInt( numStr );
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static long stringToLong(String value) {

		if( StringUtils.isBlank(value) ){
			return 0;
		}

		String numStr = StringUtils.replace(value, ",", "");

		long ret = 0;
		try{
			ret = Long.parseLong( numStr );
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 文字型の小数値から小数点以下の桁数を取得する
	 * @param floatStr 少数値
	 * @return スケール
	 */
	public static int getFloatScale(String floatStr) {

		if( StringUtils.isBlank(floatStr) ){
			return 0;
		}

		int cur = floatStr.indexOf('.');
		if( cur < 0 ){
			return 0;
		}

		return floatStr.length() - cur - 1;
	}

	/**
	 * 文字型の小数値から小数点を除去して整数値へ変換する
	 * 単純に小数点を除去するだけなので、スケールを事前に
	 * 計算しておく必要がある。
	 *
	 * @param floatStr 少数値
	 * @return 整数値
	 */
	public static long getFloatLong(String floatStr) {

		if( StringUtils.isBlank(floatStr) ){
			return 0;
		}
		String value = floatStr.replace(".", "");
		return NumberUtility.stringToLong(value);
	}

	/**
	 * long型の整数値から引数で指定したスケール分だけ
	 * 小数点を左にシフトしたdouble型の値を返す。
	 *
	 * @param value 整数値
	 * @param scale スケール
	 * @return 少数点をシフトした値
	 */
	public static BigDecimal movePointLeft(long value, int scale) {

		BigDecimal bd = new BigDecimal(value);

		return bd.movePointLeft(scale);
	}

	/**
	 * int型からBigDecimalへ変換して返す
	 * @param val int型
	 * @return BigDecimal
	 */
	public static BigDecimal integerToBigDecimal( int val ){

		return new BigDecimal( val );
	}

	/**
	 * long型からBigDecimalへ変換して返す
	 * @param val long型
	 * @return BigDecimal
	 */
	public static BigDecimal longToBigDecimal( long val ){

		return new BigDecimal( val );
	}
}