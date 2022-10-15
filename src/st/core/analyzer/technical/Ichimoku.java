package st.core.analyzer.technical;

import java.math.BigDecimal;

/**
 * このクラスは一目均衡表に関連するデータを持ちます。
 */
public class Ichimoku {

	//一目均衡表の計算に必要なデータ日数
	static public final int MIN_PRICE_DATA_SIZE = 77;
	//基準線
    private BigDecimal indexBaseLine;
    //転換線
    private BigDecimal indexChangeoverLine;
    //遅行スパン
    private BigDecimal indexLagSpan;
    //先行スパン１
    private BigDecimal indexLeadSpan1;
    //先行スパン２
    private BigDecimal indexLeadSpan2;

    //基準線を返します。
    public BigDecimal getIndexBaseLine() {
        return indexBaseLine;
    }
    
    //転換線を返します。
    public BigDecimal getIndexChangeoverLine() {
        return indexChangeoverLine;
    }
    
    //遅行スパン(26日前の株価)を返します。
    public BigDecimal getIndexLagSpan() {
        return indexLagSpan;
    }
    
    //先行スパン１を返します。
    public BigDecimal getIndexLeadSpan1() {
        return indexLeadSpan1;
    }
    
    //先行スパン２を返します。
    public BigDecimal getIndexLeadSpan2() {
        return indexLeadSpan2;
    }
    
       
    


    //基準線を返します。
    public void setIndexBaseLine(BigDecimal val) {
        indexBaseLine = val;
    }
    
    //転換線を返します。
    public void setIndexChangeoverLine(BigDecimal val) {
        indexChangeoverLine = val;
    }
    
    //遅行スパンを返します。
    public void setIndexLagSpan(BigDecimal val) {
        indexLagSpan = val;
    }
    
    //先行スパン１を返します。
    public void setIndexLeadSpan1(BigDecimal val) {
        indexLeadSpan1 = val;
    }
    
    //先行スパン２を返します。
    public void setIndexLeadSpan2(BigDecimal val) {
        indexLeadSpan2 = val;
    }
}
