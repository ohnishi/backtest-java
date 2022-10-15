package st.core.analyzer.technical;

import java.util.ArrayList;
import java.util.List;

import st.core.Price;


/**
 * 窓壁理論による分析クラス
 *
 */
public class MadoRetriever {



    protected MadoRetriever(){
    	//同一パッケージからしかインスタンス化させない
    }

    protected List<Mado> getMado(List<Price> priceList) {

        List<Mado> list = new ArrayList<Mado>();

		//一番古いデータから昨日分までのデータで窓を検索
		for( int i = priceList.size() - 2; i >= 1; i-- ){
			Price tou = priceList.get(i);
			Price zen = priceList.get(i + 1);
			long tou_high = tou.getHigh();
			long tou_low = tou.getLow();
			long zen_high = zen.getHigh();
			long zen_low = zen.getLow();

	    	for( Mado mado : list ){
	    		setMadoUme( mado, tou_high, tou_low, zen_high, zen_low);
	    	}
			if( zen_high < tou_low ){
				//窓を空けて上昇した場合
				list.add( new Mado(tou.getDate(), tou_low, zen_high) );
			}else if( zen_low > tou_high ){
				//窓をあけて下落した場合
				list.add( new Mado(tou.getDate(), tou_high, zen_low) );
			}
		}
		return list;
	}

	//この窓がまだ埋められていなければtrueを返す
	private boolean isValid( Mado mado){
		if(mado.getHigh() <= mado.getLow()){
			return false;
		}
		return true;
	}

	//高値と安値がこの窓を埋めているか判定する
	private boolean isMadoUme( Mado mado, long tou_val1, long tou_val2 ){
		long tou_high = Math.max(tou_val1, tou_val2);
		long tou_low = Math.min(tou_val1, tou_val2);

		//安値が窓の上方より上か、高値が窓の下方よりも下であれば窓埋めはしていない
		if( isValid( mado ) == false || mado.getHigh() < tou_low || mado.getLow() > tou_high ){
			return false;
		}
		return true;
	}

	private void setMadoUme( Mado mado, long tou_val1, long tou_val2, long zen_val1, long zen_val2 ){

		//高値と安値がこの窓を埋めているか判定する
		if( isMadoUme(mado, tou_val1, tou_val2) ){
			long tou_high = Math.max(tou_val1, tou_val2);
			long tou_low = Math.min(tou_val1, tou_val2);


			long zen_high = Math.max(zen_val1, zen_val2);
			long zen_low = Math.min(zen_val1, zen_val2);


			//高値と安値が窓を完全に埋めている
			if( mado.getHigh() <= tou_high && mado.getLow() >= tou_low ){
				mado.setHigh( 0 );
				mado.setLow( 0 );
			}

			//高値と安値が窓の上方と下方の間にある
			else if( mado.getHigh() >= tou_high && mado.getLow() <= tou_low ){
				//前日から株価は上昇している
				if( tou_low > zen_high ){
					mado.setLow( tou_high + 1 );
				}

				//前日から株価は下落している
				else if( tou_high < zen_low ){
					mado.setHigh( tou_low - 1 );
				}
			}

			//高値が窓の下方を埋めている
			else if( mado.getHigh() >= tou_high && mado.getLow() <= tou_high ){
				mado.setLow( tou_high + 1 );
			}

			//安値が窓の上方を埋めている
			else if( mado.getHigh() >= tou_low && mado.getLow() <= tou_low ){
				mado.setHigh( tou_low - 1 );
			}
		}
	}
}
