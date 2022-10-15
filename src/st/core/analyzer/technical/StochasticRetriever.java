
package st.core.analyzer.technical;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import st.core.Price;


/**
 * このクラスはストキャスティクスを算出して返します。
 */
class StochasticRetriever {


    protected StochasticRetriever() {
    	//同一パッケージからしかインスタンス化させない
    }

    /**
     * 集計日数 + 7以上のデータが最低限必要となる
     * @param priceList 株価データ
     * @param days 集計日数
     * @return ストキャスティクスのリスト
     */
    protected List<Stochastic> getStochastic(final List<Price> priceList, final int days) {

    	/* 計算式
			Ａ＝直近の終値
			Ｂ＝過去days日間の最高値
			Ｃ＝過去days日間の最安値
			とした場合、計算式は次のようになります。

			％Ｋ＝（Ａ－Ｃ）÷（Ｂ－Ｃ）×100（％）
			％Ｄ＝（Ａ－Ｃ）の3日間の合計÷（Ｂ－Ｃ）の3日間の合計×100（％）
			ＳＤ＝％Ｄの3日間単純移動平均（％）*/

    	int dateSize = priceList.size();
    	BigDecimal[] ks = new BigDecimal[dateSize - days];
    	long[] as = new long[dateSize - days];
    	long[] bs = new long[dateSize - days];
    	long[] cs = new long[dateSize - days];

        for (int i = days; i < priceList.size(); i++) {
            long a = priceList.get(i - days).getClose();
            long b = Long.MIN_VALUE;
            long c = Long.MAX_VALUE;
            for (int j = 1; j <= days; j++) {
                b = Math.max(b, priceList.get(i - j).getHigh());
                c = Math.min(c, priceList.get(i - j).getLow());
            }
            ks[i - days] = new BigDecimal(a - c).divide(new BigDecimal(b - c), 2, BigDecimal.ROUND_HALF_UP).multiply( new BigDecimal(100) );
            as[i - days] = a;
            bs[i - days] = b;
            cs[i - days] = c;
        }


        BigDecimal[] ds = new BigDecimal[ks.length - 3];
        for (int i = 3; i < ks.length; i++) {
        	long acSum = 0;
        	long bcSum = 0;
            for (int j = 1; j <= 3; j++) {
            	acSum += as[i - j] - cs[i - j];
            	bcSum += bs[i - j] - cs[i - j];
            }
        	ds[i - 3] = new BigDecimal(acSum).divide(new BigDecimal(bcSum), 2, BigDecimal.ROUND_HALF_UP ).multiply(new BigDecimal(100));
        }


    	List<Stochastic> list = new ArrayList<Stochastic>();

        for (int i = 3; i < ds.length; i++) {
        	BigDecimal bdSum = BigDecimal.ZERO;
            for (int j = 1; j <= 3; j++) {
            	bdSum.add(ds[i - j]);
            }
            BigDecimal slowD = bdSum.divide( new BigDecimal("3.00"), 2, BigDecimal.ROUND_HALF_UP );

            Stochastic stochastic = new Stochastic();
            stochastic.setDays(days);
            stochastic.setK(ks[i - 3]);
            stochastic.setD(ds[i - 3]);
            stochastic.setSlowD(slowD);

            list.add(stochastic);
        }

        return list;
    }

}
