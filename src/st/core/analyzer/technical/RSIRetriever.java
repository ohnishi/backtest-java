
package st.core.analyzer.technical;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import st.core.Price;



/**
 * このクラスはＲＳＩを算出して返します。
 */
class RSIRetriever {

    protected RSIRetriever() {
    	//同一パッケージからしかインスタンス化させない
    }

    /* (non-Javadoc)
     * @see jp.kaburobo.technical.RSIRetriever#getRSI(jp.kaburobo.information.Prices[], int)
     */
    protected List<RSI> getRSI(final List<Price> priceList, final int days) {
        List<RSI> list = new ArrayList<RSI>();
        for (int i = days; i < priceList.size(); i++) {
        	long a = 0;
            long b = 0;
            for (int j = 0; j < days; j++) {
            	long t = priceList.get(i - j).getClose() - priceList.get(i - j - 1).getClose();
                if (t > 0) {
                    a += t;
                } else {
                    b += -t;
                }
            }
            BigDecimal value = new BigDecimal("100.00").subtract( new BigDecimal(a).divide(new BigDecimal(a + b)).multiply(new BigDecimal("100.00")));

            RSI rsi = new RSI();
            rsi.setDays(days);
            rsi.setRsi(value);

            list.add(rsi);
        }

        return list;
    }

}
