/**
 * 
 */
package br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.entity;

import br.com.indra.avaliacao.apirest.models.banner.entity.Banner;
import br.com.indra.avaliacao.apirest.models.city.entity.City;

/**
 * @author Legilmo Oliveira
 *
 */

public class FuelPriceHistoryAggregationResult  {
	
	
	private Banner banner;
	
	private City city;

	private Double salePrice;

	private Double purchaseAmount;
	

    public FuelPriceHistoryAggregationResult(Banner banner, Double salesPrice, Double purchaseAmount) {
		this.banner         = banner;
		this.salePrice      = salesPrice;
		this.purchaseAmount = purchaseAmount;
	}
    
    public FuelPriceHistoryAggregationResult(City city, Double salesPrice, Double purchaseAmount) {
		this.city           = city;
		this.salePrice      = salesPrice;
		this.purchaseAmount = purchaseAmount;
	}


    public Banner getBanner() {
		return banner;
	}
    
    public void setBanner(Banner banner) {
		this.banner = banner;
	}
    
    public City getCity() {
		return city;
	}
    
    public void setCity(City city) {
		 this.city = city;
	}
    
    public Double getSalePrice() {
		return salePrice;
	}
    
    public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
    
    public Double getPurchaseAmount() {
		return purchaseAmount;
	}
    
    public void setPurchaseAmount(Double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
   


	
}
