package st.core.analyzer.technical;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import st.core.Price;



/**
 * このクラスは移動平均値を算出して返します。
 */
class MovingAverageRetriever {


    protected MovingAverageRetriever() {
    	//同一パッケージからしかインスタンス化させない
    }

    /* (non-Javadoc)
     * @see jp.kaburobo.technical.MovingAverageRetriever#getMovingAverage(jp.kaburobo.information.Prices[], int)
     */
    protected List<MovingAverage> getMovingAverage(final List<Price> priceList, final int days) {
        List<MovingAverage> list = new ArrayList<MovingAverage>();
        for (int i = days - 1; i < priceList.size(); i++) {
            long sum = 0;
            for (int j = 0; j < days; j++) {
                sum += priceList.get(i - j).getClose();
            }
            BigDecimal ma = new BigDecimal(sum).divide(new BigDecimal(days), 2, BigDecimal.ROUND_HALF_UP);

            MovingAverage movingAverage = new MovingAverage();
            movingAverage.setDays(days);
            movingAverage.setMovingAverage(ma);

            list.add(movingAverage);
        }

        return list;
    }

}
