/**
 * 
 */
package br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.resultquery;

/**
 * @author Legilmo Oliveira
 *
 */

public class AVGSalesPriceAndPurchaseAmount  {


	private Double avgSalesPrice;
	
	private Double avgPurchaseAmount;

	 public AVGSalesPriceAndPurchaseAmount() {
		 super();
	 }
    
    public AVGSalesPriceAndPurchaseAmount(Double avgSalesPriceByCity, Double avgPurchaseAmount ) {
    	super();
		this.avgSalesPrice = avgSalesPriceByCity;
		this.avgPurchaseAmount   = avgPurchaseAmount;
	}
    
    public Double getAvgSalesPrice() {
		return avgSalesPrice;
	}
    
    public void setAvgSalesPrice(Double avgSalesPrice) {
		this.avgSalesPrice = avgSalesPrice;
	}
    
    public Double getAvgPurchaseAmount() {
		return avgPurchaseAmount;
	}
    
    public void setAvgPurchaseAmount(Double avgPurchaseAmount) {
		this.avgPurchaseAmount = avgPurchaseAmount;
	}
	
}
