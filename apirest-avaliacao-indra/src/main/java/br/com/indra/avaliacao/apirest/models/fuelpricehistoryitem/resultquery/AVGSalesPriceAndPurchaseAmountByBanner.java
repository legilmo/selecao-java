/**
 * 
 */
package br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.resultquery;

import br.com.indra.avaliacao.apirest.models.banner.entity.Banner;

/**
 * @author Legilmo Oliveira
 *
 */

public class AVGSalesPriceAndPurchaseAmountByBanner  {

	private Banner   banner;
	
	private AVGSalesPriceAndPurchaseAmount avgSalesPriceAndPurchaseAmount;

	 public AVGSalesPriceAndPurchaseAmountByBanner() {
		 super();
	 }
    
    public AVGSalesPriceAndPurchaseAmountByBanner(Banner banner, Double avgsalesprice, Double avgpPurchaseamount ) {
    	super();
		this.banner                         = banner;
		this.avgSalesPriceAndPurchaseAmount = new AVGSalesPriceAndPurchaseAmount(avgsalesprice, avgpPurchaseamount) ;
	}
    
    public Banner getBanner() {
		return banner;
	}
    
    public void setBanner(Banner banner) {
		this.banner = banner;
	}
    
    public AVGSalesPriceAndPurchaseAmount getAvgSalesPriceAndPurchaseAmount() {
		return avgSalesPriceAndPurchaseAmount;
	}
    
    public void setAvgSalesPriceAndPurchaseAmount(AVGSalesPriceAndPurchaseAmount avgSalesPriceAndPurchaseAmount) {
		this.avgSalesPriceAndPurchaseAmount = avgSalesPriceAndPurchaseAmount;
	}
	
}
