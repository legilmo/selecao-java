package br.com.indra.avaliacao.apirest.models.fuelpricehistory.services.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.indra.avaliacao.apirest.models.fuelpricehistory.entity.FuelPriceHistory;
import br.com.indra.avaliacao.apirest.models.fuelpricehistory.repository.FuelPriceHistoryRepository;
import br.com.indra.avaliacao.apirest.models.fuelpricehistory.services.IFuelPriceHistoryService;

@Service
public class FuelPriceHistoryService implements IFuelPriceHistoryService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private FuelPriceHistoryRepository fuelPriceHistoryRepository;

    @Autowired
    public void setFuelPriceHistoryRepository(FuelPriceHistoryRepository fuelPriceHistoryRepository) {
        this.fuelPriceHistoryRepository = fuelPriceHistoryRepository;
    }

	@Override
	public Iterable<FuelPriceHistory> listAllFuelPriceHistories() {
        logger.debug("FuelPriceHistoryService::listAllProducts called");
		return fuelPriceHistoryRepository.findAll();
	}

	@Override
	public FuelPriceHistory getFuelPriceHistoryById(Integer id) {
		logger.debug("FuelPriceHistoryService::getFuelPriceHistoryById called");
		return fuelPriceHistoryRepository.findById(id).orElse(null);
	}

	@Override
	public FuelPriceHistory saveFuelPriceHistory(FuelPriceHistory fuelPriceHistory) {
		logger.debug("FuelPriceHistoryService::saveFuelPriceHistory called");
		return fuelPriceHistoryRepository.save(fuelPriceHistory);
	}

	@Override
	public void deleteFuelPriceHistory(Integer id) {
		logger.debug("FuelPriceHistoryService::deleteFuelPriceHistory called");
		fuelPriceHistoryRepository.deleteById(id);
	}

	@Override
	public FuelPriceHistory findByDistributorAndDate(int distributorID, Date collectionDate) {
		logger.debug("FuelPriceHistoryService::findByDistributorAndDate called");
		return fuelPriceHistoryRepository.findByDistributorAndDate(distributorID, collectionDate);
	}

	@Override
	public List<FuelPriceHistory> getListFuelPriceHistoryByDistributor() {
		logger.debug("FuelPriceHistoryService::findByDistributorAndDate called");
		return fuelPriceHistoryRepository.getListFuelPriceHistoryByDistributor();
	}

	@Override
	public List<FuelPriceHistory> getListFuelPriceHistoryByRegionInitials(String initials) {
		logger.debug("FuelPriceHistoryService::getListFuelPriceHistoryByRegionInitials called");
		return fuelPriceHistoryRepository.getListFuelPriceHistoryByRegionInitials(initials);
	}

	@Override
	public List<FuelPriceHistory> getListFuelPriceHistoryByDistributor(String distributorName) {
		logger.debug("FuelPriceHistoryService::getListFuelPriceHistoryByDistributor called");
		return fuelPriceHistoryRepository.getListFuelPriceHistoryByDistributor(distributorName);
	}

	@Override
	public List<FuelPriceHistory> getListFuelPriceHistoryByCollectionDate(Date collectionDate) {
		logger.debug("FuelPriceHistoryService::getListFuelPriceHistoryByCollectionDate called");
		return fuelPriceHistoryRepository.getListFuelPriceHistoryByCollectionDate(collectionDate);
	}

}
