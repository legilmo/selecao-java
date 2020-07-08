package br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.services;

import java.util.List;

import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.entity.FuelPriceHistoryItem;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.resultquery.AVGSalesPriceAndPurchaseAmountByBanner;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.resultquery.AVGSalesPriceAndPurchaseAmountByCity;

public interface IFuelPriceHistoryItemService {
    Iterable<FuelPriceHistoryItem> listAllFuelPriceHistoryItems();

    FuelPriceHistoryItem getFuelPriceHistoryItemById(Integer id);

    FuelPriceHistoryItem saveFuelPriceHistoryItem(FuelPriceHistoryItem fuelPriceHistoryItem);

    void deleteFuelPriceHistoryItem(Integer id);
    
	Double getAvgSalesPriceByCity(String cityParam);
	
	List<AVGSalesPriceAndPurchaseAmountByBanner> getListAVGSalePriceAndPurchaseAmountByBanner();
	
    List<AVGSalesPriceAndPurchaseAmountByCity> getListAVGSalePriceAndPurchaseAmountByCity();
	
	
}
