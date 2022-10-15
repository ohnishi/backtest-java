package st.core.analyzer.technical;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import st.core.Price;




/**
 * このクラスはボリンジャーバンドを算出して返します。
 */
class BollingerBandsRetriever {

    protected BollingerBandsRetriever() {
    	//同一パッケージからしかインスタンス化させない
    }

    /* (non-Javadoc)
     * @see jp.kaburobo.technical.MovingAverageRetriever#getMovingAverage(jp.kaburobo.information.Prices[], int)
     */
    protected List<BollingerBands> getBollingerBands(final List<Price> priceList, final int days) {
        List<BollingerBands> list = new ArrayList<BollingerBands>();
        for (int i = days - 1; i < priceList.size(); i++) {
        	//中央バンドを計算
            long lSum = 0;
            for (int j = 0; j < days; j++) {
            	lSum += priceList.get(i - j).getClose();
            }
            BigDecimal ma = new BigDecimal(lSum).divide( new BigDecimal(days), 2, BigDecimal.ROUND_HALF_UP );

            //標準偏差を計算
            BigDecimal bdSum = BigDecimal.ZERO;
            for (int j = 0; j < days; j++) {
            	BigDecimal avg = new BigDecimal(priceList.get(i - j).getClose());
            	avg = avg.subtract(ma);
            	avg = avg.pow(2);
            	bdSum = bdSum.add(avg);
            }
            bdSum = bdSum.divide(new BigDecimal(days), 2, BigDecimal.ROUND_HALF_UP);
            BigDecimal sigma = new BigDecimal(String.valueOf(Math.sqrt(bdSum.doubleValue())));

            MovingAverage movingAverage = new MovingAverage();
            movingAverage.setDays(days);
            movingAverage.setMovingAverage(ma);

            BollingerBands bollingerBands = new BollingerBands();
            bollingerBands.setMa(movingAverage);
            bollingerBands.setP1σ(ma.add(sigma));
            bollingerBands.setP2σ(ma.add(sigma.multiply(new BigDecimal("2.00"))));
            bollingerBands.setP3σ(ma.add(sigma.multiply(new BigDecimal("3.00"))));
            bollingerBands.setM1σ(ma.subtract(sigma));
            bollingerBands.setM2σ(ma.subtract(sigma.multiply(new BigDecimal("2.00"))));
            bollingerBands.setM3σ(ma.subtract(sigma.multiply(new BigDecimal("3.00"))));

            list.add(bollingerBands);
        }

        return list;
    }

}
