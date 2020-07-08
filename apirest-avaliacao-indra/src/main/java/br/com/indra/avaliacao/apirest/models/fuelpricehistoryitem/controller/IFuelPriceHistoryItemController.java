package br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.controller;

import java.util.List;

import br.com.indra.avaliacao.apirest.models.foundation.controller.ICRUDController;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.entity.FuelPriceHistoryItem;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.resultquery.AVGSalesPriceAndPurchaseAmountByBanner;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.resultquery.AVGSalesPriceAndPurchaseAmountByCity;

public interface IFuelPriceHistoryItemController extends ICRUDController<FuelPriceHistoryItem> {
    Double getAvgSalesPriceByCity(String cityParam);
	
	List<AVGSalesPriceAndPurchaseAmountByBanner> getListAVGSalePriceAndPurchaseAmountByBanner();
	
    List<AVGSalesPriceAndPurchaseAmountByCity> getListAVGSalePriceAndPurchaseAmountByCity();
}
