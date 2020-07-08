package br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.entity.FuelPriceHistoryItem;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.resultquery.AVGSalesPriceAndPurchaseAmountByBanner;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.resultquery.AVGSalesPriceAndPurchaseAmountByCity;

@RepositoryRestResource
public interface FuelPriceHistoryItemRepository extends CrudRepository<FuelPriceHistoryItem, Integer> {
	
	@Query(value = "SELECT item FROM FuelPriceHistoryItem item " +
                   "WHERE item.fuelPriceHistory.id = :fuelPriceHistoryId AND item.product.id = :productId ")
	public FuelPriceHistoryItem getFuelPriceHistoryItemByProductAndFuelPriceHistory(@Param("fuelPriceHistoryId") int fuelPriceHistoryId, @Param("productId") int productId );
	
	//Implementar recurso que retorne a média de preço de combustível com base no nome do município
	@Query(value = "SELECT AVG(item.salePrice) FROM FuelPriceHistoryItem item " +
                   "WHERE item.fuelPriceHistory.distributor.city.name = :cityParam  ")
	public Double getAvgSalesPriceByCity(@Param("cityParam") String cityParam);
	

	//Implementar recurso que retorne o valor médio do valor da compra e do valor da venda por bandeira
	@Query(value = "SELECT new br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.resultquery.AVGSalesPriceAndPurchaseAmountByBanner(" +
			       "  item.fuelPriceHistory.distributor.banner AS banner," +
			       "  AVG(item.salePrice) AS avgSalesPrice, AVG(item.purchaseAmount) AS avgPurchaseAmount) " +
			       "  FROM FuelPriceHistoryItem item GROUP BY item.fuelPriceHistory.distributor.banner ORDER BY item.fuelPriceHistory.distributor.banner.name ")
	public List<AVGSalesPriceAndPurchaseAmountByBanner> getListAVGSalePriceAndpurchaseAmountByBanner();
	
	//Implementar recurso que retorne o valor médio do valor da compra e do valor da venda por município
	@Query(value = "SELECT new br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.resultquery.AVGSalesPriceAndPurchaseAmountByCity(" +
		       "  item.fuelPriceHistory.distributor.city AS city," +
		       "  AVG(item.salePrice) AS avgSalesPrice, AVG(item.purchaseAmount) AS avgPurchaseAmount)  " +
		       "  FROM FuelPriceHistoryItem item GROUP BY item.fuelPriceHistory.distributor.city ORDER BY item.fuelPriceHistory.distributor.city.name ")
    public List<AVGSalesPriceAndPurchaseAmountByCity> getListAVGSalePriceAndpurchaseAmountByCity();
	
	@Transactional
	@Query(value = "DELETE FROM FuelPriceHistoryItem item WHERE item.fuelPriceHistory.id = :fuelPriceHistoryId")
	public void deleteAllItemsByFuelPriceHistory(@Param("fuelPriceHistoryId") int fuelPriceHistoryId);
	
	
}
