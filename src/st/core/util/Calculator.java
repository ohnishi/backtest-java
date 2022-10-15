package st.core.util;

import java.math.BigDecimal;
import java.util.List;

import st.core.Price;



/**
 * テクニカル計算クラス
 *
 */
public class Calculator {

	//BigDecimal型の１００を定義
	private static final BigDecimal HUNDRED = new BigDecimal("100");


	//指定した期間の平均騰落率を返す
	public static BigDecimal trendPercentage( List<Price> priceList, int days ){

		BigDecimal val = new BigDecimal("0.00");
    	if( priceList.size() > days ){
	        for(int i = days; i > 0; i--){
	        	long zen = priceList.get(i).getClose();
	        	long tou = priceList.get(i - 1).getClose() ;
	        	val = val.add(Calculator.beforePercentage(tou, zen));
	        }
	        val = val.divide( NumberUtility.integerToBigDecimal(days), BigDecimal.ROUND_HALF_UP );
    	}
		return val;
	}

	//窓空け比率を返す
	public static BigDecimal windowWall( Price tou, Price zen ){

		BigDecimal val = BigDecimal.ZERO;
		long tou_high = tou.getHigh();
		long tou_low = tou.getLow();
		long zen_high = zen.getHigh();
		long zen_low = zen.getLow();

		if( zen_high < tou_low ){
			//窓を空けて上昇した場合
			val = tourakuPercentage(tou_low, zen_high);
		}else if( zen_low > tou_high ){
			//窓をあけて下落した場合
			val = tourakuPercentage(tou_high, zen_low);
		}
    	return val;
	}


	//前日比騰落率を返す
	public static BigDecimal beforePercentage( List<Price> priceList ){

		BigDecimal val = BigDecimal.ZERO;
    	if( priceList.size() >= 2 ){
    		val = Calculator.beforePercentage(priceList.get(0).getClose(), priceList.get(1).getClose());
    	}
		return val;
	}

	//前日比騰落率を返す
	public static BigDecimal beforePercentage( long tou, long zen ){

		return tourakuPercentage(tou, zen);
	}

	//当日比騰落率を返す
	public static BigDecimal todayPercentage( long open, long close ){

		return tourakuPercentage(close, open);
	}

	//値動き幅率を返す
	public static BigDecimal priceMoveRange( long low, long high ){

		return tourakuPercentage(high, low);
	}

	//出来高騰落率を返す
	public static BigDecimal dealingsPercentage( long touDeal, long zenDeal ){

		return tourakuPercentage(touDeal, zenDeal);
	}

	//短期移動平均と長期移動平均の乖離率を返す
	public static BigDecimal movingAverageRange( long mashort, long malong ){

		return tourakuPercentage(malong, mashort);
	}

	//窓の幅率を返す
	public static BigDecimal madoRange( long low, long high ){

		return tourakuPercentage(high, low);
	}

	//窓との乖離率を返す
	public static BigDecimal madoKairiRange( long price, long mado ){

		return tourakuPercentage(mado, price);
	}

	//上ひげの騰落率を返す
	public static BigDecimal topHige( long open, long close, long high ){

		long whigh = Math.max(open, close);
		return tourakuPercentage(high, whigh);
	}

	//下ひげの騰落率を返す
	public static BigDecimal bottomHige( long open, long close, long low ){

		long wlow = Math.min(open, close);
		return tourakuPercentage(low, wlow).negate();
	}

	//移動平均乖離率を返す
	public static BigDecimal maPercentage( long close, BigDecimal ma ){

		//移動平均が０以下は計算不可能なので０を返す
		if( ma.doubleValue() <= 0d ){
			return BigDecimal.ZERO;
		}

		//val = (close - ma) / ma * 100d;
		BigDecimal val = new BigDecimal(close);
		val = val.subtract(ma);
		val = val.divide(ma, BigDecimal.ROUND_HALF_UP);
		val = val.multiply(HUNDRED);

		return val;
	}

	//指定した期間の移動平均値を返す
	public static BigDecimal simpleMovingAverage( List<Price> priceList, int days ){

		BigDecimal val = new BigDecimal("0.00");
    	if( priceList.size() >= days ){
	        for(int i = 0; i < days; i++){
	        	val = val.add(NumberUtility.longToBigDecimal(priceList.get(i).getClose()));
	        }
	        val = val.divide(NumberUtility.integerToBigDecimal(days), BigDecimal.ROUND_HALF_UP);
    	}
		return val;
	}

	/**
	 * リストに渡されたデータの値動きの平均値を返します。
	 * @param priceList ろうそく足データ
	 * @return 足ごとの値動き平均値
	 */
	public static long getVolatilityAverage( List<Price> priceList ){

		long va = 0;
		for( Price price : priceList ){
			va += price.getHigh() - price.getLow();
		}
		return va / priceList.size();
	}

	//引数AとBの騰落率を返す
	public static BigDecimal tourakuPercentage( long a, long b ){

		return percentage(a - b, b);
	}

	//引数AとBのパーセンテージを返す
	public static BigDecimal percentage( long a, long b ){

		BigDecimal bd1 = new BigDecimal(String.valueOf(a));
		BigDecimal bd2 = new BigDecimal( String.valueOf(b) );

		return bd1.divide(bd2, 2, BigDecimal.ROUND_HALF_UP).multiply(HUNDRED);
	}

	//現在株価に対する値幅を取得する
	public static int getPriceRange( long price ){

		if( 100 > price ){
			return 30;
		}
		if( 200 > price ){
			return 50;
		}
		if( 500 > price ){
			return 80;
		}
		if( 1000 > price ){
			return 100;
		}
		if( 1500 > price ){
			return 200;
		}
		if( 2000 > price ){
			return 300;
		}
		if( 3000 > price ){
			return 400;
		}
		if( 5000 > price ){
			return 500;
		}
		if( 10000 > price ){
			return 1000;
		}
		if( 20000 > price ){
			return 2000;
		}
		if( 30000 > price ){
			return 3000;
		}
		if( 50000 > price ){
			return 4000;
		}
		if( 70000 > price ){
			return 5000;
		}
		if( 100000 > price ){
			return 10000;
		}
		if( 150000 > price ){
			return 20000;
		}
		if( 200000 > price ){
			return 30000;
		}
		if( 300000 > price ){
			return 40000;
		}
		if( 500000 > price ){
			return 50000;
		}
		if( 1000000 > price ){
			return 100000;
		}
		if( 1500000 > price ){
			return 200000;
		}
		if( 2000000 > price ){
			return 300000;
		}
		if( 3000000 > price ){
			return 400000;
		}
		if( 5000000 > price ){
			return 500000;
		}
		if( 10000000 > price ){
			return 1000000;
		}
		if( 15000000 > price ){
			return 2000000;
		}
		if( 20000000 > price ){
			return 3000000;
		}
		if( 30000000 > price ){
			return 4000000;
		}
		if( 50000000 > price ){
			return 5000000;
		}
		if( 50000000 <= price ){
			return 10000000;
		}
		return -1;
	}

	//株価に対する呼値を返す
	public static int getCallPrice( long price ){

		if( 2000 >= price ){
			return 1;
		}
		if( 3000 >= price ){
			return 5;
		}
		if( 30000 >= price ){
			return 10;
		}
		if( 50000 >= price ){
			return 50;
		}
		if( 100000 >= price ){
			return 100;
		}
		if( 1000000 >= price ){
			return 1000;
		}
		if( 20000000 >= price ){
			return 10000;
		}
		if( 30000000 >= price ){
			return 50000;
		}
		if( 30000000 < price ){
			return 100000;
		}
		return -1;
	}
}
