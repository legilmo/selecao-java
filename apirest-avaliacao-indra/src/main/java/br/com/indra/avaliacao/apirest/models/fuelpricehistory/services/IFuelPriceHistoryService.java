package br.com.indra.avaliacao.apirest.models.fuelpricehistory.services;

import java.util.Date;
import java.util.List;

import br.com.indra.avaliacao.apirest.models.fuelpricehistory.entity.FuelPriceHistory;

public interface IFuelPriceHistoryService {
    Iterable<FuelPriceHistory> listAllFuelPriceHistories();

    FuelPriceHistory getFuelPriceHistoryById(Integer id);

    FuelPriceHistory saveFuelPriceHistory(FuelPriceHistory fuelPriceHistory);

    void deleteFuelPriceHistory(Integer id);
    
	FuelPriceHistory findByDistributorAndDate(int distributorID, Date collectionDate);	

	List<FuelPriceHistory> getListFuelPriceHistoryByDistributor();
	
	List<FuelPriceHistory> getListFuelPriceHistoryByRegionInitials(String initials);
	
	List<FuelPriceHistory> getListFuelPriceHistoryByDistributor(String distributorName);
	
    List<FuelPriceHistory> getListFuelPriceHistoryByCollectionDate(Date collectionDate);
    
}
