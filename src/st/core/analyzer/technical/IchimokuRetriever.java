package st.core.analyzer.technical;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import st.core.Price;



/**
 * このクラスは一目均衡表を算出して返します。
 */
class IchimokuRetriever {


    protected IchimokuRetriever() {
    	//同一パッケージからしかインスタンス化させない
    }


    protected List<Ichimoku> getIchimoku(final List<Price> priceList) {


        List<Ichimoku> list = new ArrayList<Ichimoku>();

        int priceLen = priceList.size();
        /*if(priceLen < Ichimoku.MIN_PRICE_DATA_SIZE){
            return list;
        }*/

        for (int i = 0; i < priceLen - (26 - 1); i++) {

            Ichimoku ichimoku = new Ichimoku();

        	//基準線を計算
            BigDecimal high = new BigDecimal(String.valueOf(Double.MIN_VALUE));
            BigDecimal low = new BigDecimal(String.valueOf(Double.MAX_VALUE));
            for (int j = i; j < i + 26; j++) {
            	high = high.max(new BigDecimal(priceList.get(j).getHigh()));
            	low = low.min(new BigDecimal(priceList.get(j).getLow()));
            }
        	//基準線
            ichimoku.setIndexBaseLine(high.add(low).divide(new BigDecimal("2.00"), 2, BigDecimal.ROUND_HALF_UP));

        	//転換線を計算
            high = new BigDecimal(String.valueOf(Double.MIN_VALUE));
            low = new BigDecimal(String.valueOf(Double.MAX_VALUE));
            for (int j = i; j < i + 9; j++) {
            	high = high.max(new BigDecimal(priceList.get(j).getHigh()));
            	low = low.min(new BigDecimal(priceList.get(j).getLow()));
            }
            //転換線
            ichimoku.setIndexChangeoverLine(high.add(low).divide(new BigDecimal("2.00"), 2, BigDecimal.ROUND_HALF_UP));

        	//遅行スパンを計算
            //遅行スパン
            ichimoku.setIndexLagSpan(new BigDecimal(priceList.get(i + 26 - 1).getClose()));

            list.add(ichimoku);
        }

       	//先行スパン１
        for (int i = 26 - 1; i < list.size(); i++) {
        	Ichimoku ichimoku = list.get(i);
        	BigDecimal indexLeadSpan1 = ichimoku.getIndexBaseLine().add(ichimoku.getIndexChangeoverLine()).divide(new BigDecimal("2.00"), 2, BigDecimal.ROUND_HALF_UP);
            list.get(i - (26 - 1)).setIndexLeadSpan1(indexLeadSpan1);
        }

       	//先行スパン２
        for (int i = 26 - 1; i < priceLen - (52 - 1); i++) {
        	int setCur = i - (26 - 1);
        	if (setCur >= list.size()) {
        		break;
        	}

        	//先行スパン２を計算
            BigDecimal high = new BigDecimal(String.valueOf(Double.MIN_VALUE));
            BigDecimal low = new BigDecimal(String.valueOf(Double.MAX_VALUE));
            for (int j = i; j < i + 52; j++) {
            	high = high.max(new BigDecimal(priceList.get(j).getHigh()));
            	low = low.min(new BigDecimal(priceList.get(j).getLow()));
            }
        	//基準線
            BigDecimal indexLeadSpan2 = high.add(low).divide(new BigDecimal("2.00"), 2, BigDecimal.ROUND_HALF_UP);

            list.get(setCur).setIndexLeadSpan2(indexLeadSpan2);
        }

        return list;
    }
}
