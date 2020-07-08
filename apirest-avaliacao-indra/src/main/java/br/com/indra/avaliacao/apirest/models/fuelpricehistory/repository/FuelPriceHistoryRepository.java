package br.com.indra.avaliacao.apirest.models.fuelpricehistory.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.indra.avaliacao.apirest.models.fuelpricehistory.entity.FuelPriceHistory;

@RepositoryRestResource
public interface FuelPriceHistoryRepository extends CrudRepository<FuelPriceHistory, Integer> {
	
	@Query(value = "SELECT fph FROM FuelPriceHistory fph " +
                   "WHERE fph.distributor.id = :distributorId AND fph.collectionDate = :collectionDate  ")	
	public FuelPriceHistory findByDistributorAndDate(@Param("distributorId") int distributorID, @Param("collectionDate") Date collectionDate);	

	@Query(value = "SELECT fph FROM FuelPriceHistory fph ORDER BY fph.distributor.name ")
	public List<FuelPriceHistory> getListFuelPriceHistoryByDistributor();
	
	//Implementar recurso que retorne todas as informações importadas por sigla da região
	@Query(value = "SELECT fph FROM FuelPriceHistory fph " +
            "WHERE fph.distributor.city.state.region.initials = :initials ")
	public List<FuelPriceHistory> getListFuelPriceHistoryByRegionInitials(@Param("initials") String initials);
	
	//3-Implementar recurso que retorne os dados agrupados por distribuidora
	@Query(value = "SELECT fph FROM FuelPriceHistory fph WHERE fph.distributor.name = :distributorName ORDER BY fph.collectionDate DESC")
	public List<FuelPriceHistory> getListFuelPriceHistoryByDistributor(@Param("distributorName") String distributorName);
	
	//4-Implementar recurso que retorne os dados agrupados pela data da coleta
	@Query(value = "SELECT fph FROM FuelPriceHistory fph WHERE fph.collectionDate = :collectionDate  ORDER BY fph.distributor.name  ")
	public List<FuelPriceHistory> getListFuelPriceHistoryByCollectionDate(@Param("collectionDate") Date collectionDate);
	
	
}
