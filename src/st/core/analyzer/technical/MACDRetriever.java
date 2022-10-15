package st.core.analyzer.technical;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import st.core.Price;



/**
 * このクラスはＭＡＣＤを算出して返します。
 */
class MACDRetriever {

    protected MACDRetriever() {
    	//同一パッケージからしかインスタンス化させない
    }

    /* (non-Javadoc)
     * @see jp.kaburobo.technical.MACDRetriever#getMACD(jp.kaburobo.information.Prices[], int, int)
     */
    protected List<MACD> getMACD(final List<Price> priceList,
    								final int shortDmaCnt,
    								final int longDmaCnt,
    								final int signalDays) {


    	int shortDma = shortDmaCnt;
    	int longDma = longDmaCnt;

        List<MACD> list = new ArrayList<MACD>();
        BigDecimal shortEma = BigDecimal.ZERO;
        BigDecimal longEma = BigDecimal.ZERO;
        MACD[] macd;
        int i = 0;

        int priceLen = priceList.size();
        if (priceLen < longDma) {
            return list;
        }

        int macdLen = priceLen - longDma + 1;
        macd = new MACD[macdLen];

        if (longDma < shortDma) {
            int t = longDma;
            longDma = shortDma;
            shortDma = t;
        }

        for (i = priceLen - 1; i > priceLen - (longDma - shortDma); i--) {
            longEma = longEma.add(new BigDecimal(priceList.get(i).getClose()));
        }
        for (; i > priceLen - longDma; i--) {
            shortEma = shortEma.add(new BigDecimal(priceList.get(i).getClose()));
        }
        longEma = longEma.add(shortEma);
        shortEma = shortEma.divide(new BigDecimal(shortDma), 2, BigDecimal.ROUND_HALF_UP);
        longEma = longEma.divide(new BigDecimal(longDma), 2, BigDecimal.ROUND_HALF_UP);

        macd[macdLen - 1] = new MACD();
        macd[macdLen - 1].setMACD(shortEma.subtract(longEma));

        for (; i >= 0; i--) {
            //shortEma = shortEma + 2.0 / (shortDma + 1.0) * (priceList.get(i).getClose() - shortEma);
            //longEma = longEma + 2.0 / (longDma + 1.0) * (priceList.get(i).getClose() - longEma);
            //shortEma = shortEma.add(new BigDecimal("2.00")).divide(new BigDecimal(shortDma + 1), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(priceList.get(i).getClose()).subtract(shortEma));
            //longEma = longEma.add(new BigDecimal("2.00")).divide(new BigDecimal(longDma + 1), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(priceList.get(i).getClose()).subtract(longEma));
        	
        	BigDecimal a1 = shortEma.add(new BigDecimal("2.00"));
        	BigDecimal a2 = a1.divide(new BigDecimal(shortDma + 1), 2, BigDecimal.ROUND_HALF_UP);
        	BigDecimal a3 = a2.multiply(new BigDecimal(priceList.get(i).getClose()));
        	shortEma = a3.subtract(shortEma);
        	BigDecimal b1 = longEma.add(new BigDecimal("2.00"));
        	BigDecimal b2 = b1.divide(new BigDecimal(longDma + 1), 2, BigDecimal.ROUND_HALF_UP);
        	BigDecimal b3 = b2.multiply(new BigDecimal(priceList.get(i).getClose()));
        	longEma = b3.subtract(longEma);
            
            int macdCur = i;
            macd[macdCur] = new MACD();
            macd[macdCur].setMACD(shortEma.subtract(longEma));

            MovingAverage shortMovingAverage = new MovingAverage();
            shortMovingAverage.setDays(shortDma);
            shortMovingAverage.setMovingAverage(shortEma);

            macd[macdCur].setShortMovingAverage(shortMovingAverage);

            MovingAverage longMovingAverage = new MovingAverage();
            longMovingAverage.setDays(longDma);
            longMovingAverage.setMovingAverage(longEma);

            macd[macdCur].setLongMovingAverage(longMovingAverage);
        }

        for (i = signalDays - 1; i < macdLen; i++) {
        	BigDecimal sum = BigDecimal.ZERO;
            for (int j = 0; j < signalDays; j++) {
                sum = sum.add(macd[i - j].getMACD());
            }
            BigDecimal signal = sum.divide(new BigDecimal(signalDays), 2, BigDecimal.ROUND_HALF_UP);
            int macdCur = i - signalDays + 1;
            macd[macdCur].setSignal(signal);
            macd[macdCur].setOSCI(macd[macdCur].getMACD().subtract(signal));
            list.add(macd[macdCur]);
        }

        return list;
    }

}
