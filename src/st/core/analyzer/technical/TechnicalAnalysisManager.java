package st.core.analyzer.technical;

import java.util.List;

import st.core.Price;



/**
 * 各テクニカル分析の結果を取得するための機能を提供します。
 */
public class TechnicalAnalysisManager {
	

	private static TechnicalAnalysisManager technicalAnalysisManager = null;
	
	private BollingerBandsRetriever bollingerBandsRetriever = null;
    private MovingAverageRetriever movingAverageRetriever = null;
    private MACDRetriever macdRetriever = null;
    private RSIRetriever rsiRetriever = null;
    private DMIRetriever dmiRetriever = null;
    private IchimokuRetriever ichimokuRetriever = null;
    private MadoRetriever madoRetriever = null;
    private StochasticRetriever stochasticRetriever = null;
    private AverageStickRetriever averageStickRetriever = null;

    private TechnicalAnalysisManager(){
    	bollingerBandsRetriever = new BollingerBandsRetriever();
    	movingAverageRetriever = new MovingAverageRetriever();
		macdRetriever = new MACDRetriever();
		rsiRetriever = new RSIRetriever();
		dmiRetriever = new DMIRetriever();
		ichimokuRetriever = new IchimokuRetriever();
		madoRetriever = new MadoRetriever();
		stochasticRetriever = new StochasticRetriever();
		averageStickRetriever = new AverageStickRetriever();
    }
    
	public static synchronized TechnicalAnalysisManager getInstance() {
		if (technicalAnalysisManager == null) {
			technicalAnalysisManager = new TechnicalAnalysisManager();
		}
		return technicalAnalysisManager;
	}

	/**
	 * 指定された株価のリストと集計日数でボリンジャーバンド値を計算して返します。
	 * @param prices 株価
	 * @param days 集計日数
	 * @return MovingAverageオブジェクトが要素であるList
	 * リストの先頭からdays-1の数の分までは計算できないため、
	 * 最低days個の要素を必要とし、
	 * 返り値のListの要素数はprices.length-days+1となります。
	 */
    public List<BollingerBands> getBollingerBands(List<Price> priceList, int days){
        return bollingerBandsRetriever.getBollingerBands(priceList, days);
    }

	/**
	 * 指定された株価のリストと集計日数で移動平均値を計算して返します。
	 * @param prices 株価
	 * @param days 集計日数
	 * @return MovingAverageオブジェクトが要素であるList
	 * リストの先頭からdays-1の数の分までは計算できないため、
	 * 最低days個の要素を必要とし、
	 * 返り値のListの要素数はprices.length-days+1となります。
	 */
    public List<MovingAverage> getMovingAverage(List<Price> priceList, int days){
        return movingAverageRetriever.getMovingAverage(priceList, days);
    }

    /**
	 * 指定された株価のリストと移動平均線の短期線期間、長期線期間でのMACDを返します。
	 * @param prices 株価のリスト
     * @param shortDma 移動平均線の短期線期間の集計日数
     * @param longDma 移動平均線の長期線期間の集計日数
     * @return MACDオブジェクトが要素であるList
	 * リストの先頭からlongDma-1の数の分までは計算できず、
	 * またシグナルの計算に５日移動平均を用いるため
	 * 返り値のListの要素数はprices.length-longDma-3となります。
     */
    public List<MACD> getMACD(List<Price> priceList, int shortDma, int longDma, int signalDays){
        return macdRetriever.getMACD(priceList, shortDma, longDma, signalDays);
    }

    /**
	 * 指定された株価のリストと集計日数での相対力指数RSIを返します。
	 * @param prices 株価のリスト
     * @param days 集計日数
     * @return RSIオブジェクトが要素であるList
	 * リストの先頭からdaysの数の分までは計算できないため、
	 * 返り値のListの要素数はprices.length-daysとなります。
     */
    public List<RSI> getRSI(List<Price> priceList, int days){
        return rsiRetriever.getRSI(priceList, days);
    }

    /**
	 * 指定された株価のリストと集計日数でのDMIを返します。
	 * @param prices 株価のリスト
     * @param days 集計日数
     * @return DMIオブジェクトが要素であるList
	 * リストの先頭からdaysの数の分までは計算できないため、
	 * 返り値のListの要素数はprices.length-daysとなります。
     */
    public List<DMI> getDMI(List<Price> priceList, int days){
        return dmiRetriever.getDMI(priceList, days);
    }

    /**
	 * 指定された株価のリストでの一目均衡表を返します。
	 * @param prices 株価のリスト
     * @return 一目均衡表オブジェクトが要素であるList
     */
    public List<Ichimoku> getIchimoku(List<Price> priceList){
        return ichimokuRetriever.getIchimoku(priceList);
    }

    /**
	 * 指定された株価のリストと集計日数での窓を返します。
	 * @param prices 株価のリスト
     * @return 窓オブジェクトが要素であるList
     */
    public List<Mado> getMado(List<Price> priceList){
        return madoRetriever.getMado(priceList);
    }

    /**
	 * 指定された株価のリストと集計日数でのストキャスティクスを返します。
	 * @param prices 株価のリスト
     * @param days 集計日数
     * @return ストキャスティクスが要素であるList
     */
    public List<Stochastic> getStochastic(List<Price> priceList, int days){
        return stochasticRetriever.getStochastic(priceList, days);
    }

	/**
	 * 指定されたローソク足の価格リストで平均足を計算して返します。
	 * @param prices ローソク足の価格リスト
	 * @return 平均足のリスト
	 */
    public List<AverageStick> getAverageStick(List<Price> priceList){
        return averageStickRetriever.getAverageStick(priceList);
    }
}
