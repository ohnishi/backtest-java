package st.core.analyzer.technical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import st.core.Price;

/**
 * このクラスは移動平均値を算出して返します。
 */
class AverageStickRetriever {

	protected AverageStickRetriever() {
		// 同一パッケージからしかインスタンス化させない
	}

	/**
	 * 平均足を計算して返します
	 * @param priceList ローソク足リスト
	 * @return 平均足リスト
	 */
	protected List<AverageStick> getAverageStick(final List<Price> priceList) {

		List<AverageStick> list = new ArrayList<AverageStick>();
		AverageStick beforeAverageStick = null;

		for (int i = priceList.size() - 2; i > 0; i--) {

			Price nowPrice = priceList.get(i);
			Price beforePrice = priceList.get(i + 1);
			AverageStick averageStick = new AverageStick();
			if( beforeAverageStick == null ){
				//初回の計算のみ始値の計算方法が異なる
				averageStick.setOpen((beforePrice.getOpen()+beforePrice.getHigh()+beforePrice.getLow()+beforePrice.getClose())/4);
				averageStick.setClose((nowPrice.getOpen()+nowPrice.getHigh()+nowPrice.getLow()+nowPrice.getClose())/4);
			}else{
				averageStick.setOpen((beforeAverageStick.getOpen()+beforeAverageStick.getClose())/2);
				averageStick.setClose((nowPrice.getOpen()+nowPrice.getHigh()+nowPrice.getLow()+nowPrice.getClose())/4);
			}
			averageStick.setHigh(nowPrice.getHigh());
			averageStick.setLow(nowPrice.getLow());
			averageStick.setCode(nowPrice.getCode());
			averageStick.setDate(nowPrice.getDate());

			list.add(averageStick);
			//前日の平均足として次のループで使用する
			beforeAverageStick = averageStick;
		}
		Collections.reverse(list);
		return list;
	}

}
