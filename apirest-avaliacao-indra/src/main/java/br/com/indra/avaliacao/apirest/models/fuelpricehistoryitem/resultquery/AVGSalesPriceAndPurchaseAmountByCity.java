/**
 * 
 */
package br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.resultquery;

import br.com.indra.avaliacao.apirest.models.city.entity.City;

/**
 * @author Legilmo Oliveira
 *
 */

public class AVGSalesPriceAndPurchaseAmountByCity  {

	private City   city;
	private AVGSalesPriceAndPurchaseAmount avgSalesPriceAndPurchaseAmount;

	 public AVGSalesPriceAndPurchaseAmountByCity() {
		 super();
	 }
    
    public AVGSalesPriceAndPurchaseAmountByCity(City city, Double avgsalesprice, Double avgpPurchaseamount) {
    	super();
		this.city                           = city;
		this.avgSalesPriceAndPurchaseAmount = new AVGSalesPriceAndPurchaseAmount(avgsalesprice, avgpPurchaseamount);
    }
    
    public City getCity() {
		return city;
	}
    
    public void setCity(City city) {
		 this.city = city;
	}
    
    public AVGSalesPriceAndPurchaseAmount getAvgSalesPriceAndPurchaseAmount() {
		return avgSalesPriceAndPurchaseAmount;
	}
    
    public void setAvgSalesPriceAndPurchaseAmount(AVGSalesPriceAndPurchaseAmount avgSalesPriceAndPurchaseAmount) {
		this.avgSalesPriceAndPurchaseAmount = avgSalesPriceAndPurchaseAmount;
	}
	
}
