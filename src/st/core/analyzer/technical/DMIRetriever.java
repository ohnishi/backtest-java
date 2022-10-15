package st.core.analyzer.technical;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import st.core.Price;



/**
 * このクラスはDMIを算出して返します。
 */
class DMIRetriever {

	static final private BigDecimal ONE_HUNDRED = new BigDecimal("100.00");
	static final private BigDecimal ZERO = new BigDecimal("0.0000");
	static final private BigDecimal ONE = new BigDecimal("1.0000");

	protected DMIRetriever() {
    	//同一パッケージからしかインスタンス化させない
    }

	/*
	 * (non-Javadoc)
	 *
	 * @see jp.kaburobo.technical.MACDRetriever#getMACD(jp.kaburobo.information.Prices[],
	 *      int, int)
	 */
	protected List<DMI> getDMI(List<Price> priceList, int days) {
		List<DMI> list = new ArrayList<DMI>();
		for (int i = days; i < priceList.size(); i++) {
			long[] pdms = new long[days];
			long[] mdms = new long[days];
			long[] trs = new long[days];
			for (int j = 0; j < days; j++) {

				Price tou_pr = priceList.get(i - j - 1);
				Price zen_pr = priceList.get(i - j);

				long zen_high = zen_pr.getHigh();
				long zen_low = zen_pr.getLow();
				long zen_close = zen_pr.getClose();
				long tou_high = tou_pr.getHigh();
				long tou_low = tou_pr.getLow();

				//上昇幅
				long pdm = tou_high - zen_high;
				//下落幅
				long mdm = zen_low - tou_low;

				//DM計算
				if (pdm <= 0 && mdm <= 0) {
					pdms[j] = 0;
					mdms[j] = 0;
				} else if (pdm > mdm) {
					pdms[j] = pdm;
					mdms[j] = 0;
				} else if (pdm < mdm) {
					pdms[j] = 0;
					mdms[j] = mdm;
				} else {
					pdms[j] = 0;
					mdms[j] = 0;
				}

				//TR計算
				long tr_a = tou_high - tou_low;
				long tr_b = tou_high - zen_close;
				long tr_c = zen_close - tou_low;
				trs[j] = Math.max(tr_a, Math.max(tr_b, tr_c));
			}

			long sum_pdm = arraysSum(pdms);
			long sum_mdm = arraysSum(mdms);
			long sum_tr = Math.max(arraysSum(trs), 1l);
			BigDecimal pdi = new BigDecimal(sum_pdm).divide( new BigDecimal(sum_tr), 2, BigDecimal.ROUND_HALF_UP ).multiply(ONE_HUNDRED);
			BigDecimal mdi = new BigDecimal(sum_mdm).divide( new BigDecimal(sum_tr), 2, BigDecimal.ROUND_HALF_UP ).multiply(ONE_HUNDRED);
			BigDecimal pmdi = pdi.add(mdi);
			if( ZERO.equals(pmdi) ){
				pmdi = ONE;
			}
			try{
				BigDecimal dx = pdi.subtract(mdi).abs().divide( pmdi, 2, BigDecimal.ROUND_HALF_UP ).multiply(ONE_HUNDRED);

				DMI dmi = new DMI();
				dmi.setPdi(pdi);
				dmi.setMdi(mdi);
				dmi.setAdx(dx);
				dmi.setDays(days);

				list.add(dmi);
			}catch(java.lang.ArithmeticException arithmeticException){
				throw arithmeticException;
			}
		}

		return list;
	}

	private long arraysSum(long[] array) {

		long res = 0;
		for (int i = 0; i < array.length; i++) {
			res += array[i];
		}
		return res;
	}
}
