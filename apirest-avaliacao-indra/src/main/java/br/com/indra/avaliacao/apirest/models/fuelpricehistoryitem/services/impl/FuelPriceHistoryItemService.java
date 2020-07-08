package br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.entity.FuelPriceHistoryItem;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.repository.FuelPriceHistoryItemRepository;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.resultquery.AVGSalesPriceAndPurchaseAmountByBanner;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.resultquery.AVGSalesPriceAndPurchaseAmountByCity;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.services.IFuelPriceHistoryItemService;

@Service
public class FuelPriceHistoryItemService implements IFuelPriceHistoryItemService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private FuelPriceHistoryItemRepository fuelPriceHistoryItemRepository;

    @Autowired
    public void setFuelPriceHistoryItemRepository(FuelPriceHistoryItemRepository fuelPriceHistoryItemRepository) {
        this.fuelPriceHistoryItemRepository = fuelPriceHistoryItemRepository;
    }

	@Override
	public Iterable<FuelPriceHistoryItem> listAllFuelPriceHistoryItems() {
        logger.debug("FuelPriceHistoryItemService::listAllFuelPriceHistoryItems called");
		return fuelPriceHistoryItemRepository.findAll();
	}

	@Override
	public FuelPriceHistoryItem getFuelPriceHistoryItemById(Integer id) {
		logger.debug("FuelPriceHistoryItemService::getFuelPriceHistoryById called");
		return fuelPriceHistoryItemRepository.findById(id).orElse(null);
	}

	@Override
	public FuelPriceHistoryItem saveFuelPriceHistoryItem(FuelPriceHistoryItem fuelPriceHistoryItem) {
		logger.debug("FuelPriceHistoryItemService::saveFuelPriceHistoryItem called");
		return fuelPriceHistoryItemRepository.save(fuelPriceHistoryItem);
	}

	@Override
	public void deleteFuelPriceHistoryItem(Integer id) {
		logger.debug("FuelPriceHistoryItemService::deleteFuelPriceHistoryItem called");
		fuelPriceHistoryItemRepository.deleteById(id);
	}

	@Override
	public Double getAvgSalesPriceByCity(String cityParam) {
		logger.debug("FuelPriceHistoryItemService::getAvgSalesPriceByCity called");
		return fuelPriceHistoryItemRepository.getAvgSalesPriceByCity(cityParam);
	}

	@Override
	public List<AVGSalesPriceAndPurchaseAmountByBanner> getListAVGSalePriceAndPurchaseAmountByBanner() {
		logger.debug("FuelPriceHistoryItemService::getListAVGSalePriceAndpurchaseAmountByBanner called");
		return fuelPriceHistoryItemRepository.getListAVGSalePriceAndpurchaseAmountByBanner();
	}

	@Override
	public List<AVGSalesPriceAndPurchaseAmountByCity> getListAVGSalePriceAndPurchaseAmountByCity() {
		logger.debug("FuelPriceHistoryItemService::getListAVGSalePriceAndpurchaseAmountByCity called");
		return fuelPriceHistoryItemRepository.getListAVGSalePriceAndpurchaseAmountByCity();
	}

}
