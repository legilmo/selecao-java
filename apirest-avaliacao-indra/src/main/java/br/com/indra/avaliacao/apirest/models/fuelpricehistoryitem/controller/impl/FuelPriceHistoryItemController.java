package br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.indra.avaliacao.apirest.models.fuelpricehistory.entity.FuelPriceHistory;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.controller.IFuelPriceHistoryItemController;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.entity.FuelPriceHistoryItem;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.resultquery.AVGSalesPriceAndPurchaseAmountByBanner;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.resultquery.AVGSalesPriceAndPurchaseAmountByCity;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.services.IFuelPriceHistoryItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/v1/fuelpricehistoryIitem")
@Api(value="1", description="Operations pertaining to FuelPriceHistoryItem in test")
public class FuelPriceHistoryItemController implements IFuelPriceHistoryItemController {
	
	private IFuelPriceHistoryItemService fuelPriceHistoryItemService;
	

	@Autowired
    public void setFuelPriceHistoryService(IFuelPriceHistoryItemService fuelPriceHistoryItemService) {
		this.fuelPriceHistoryItemService = fuelPriceHistoryItemService;
	}
	
    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "View a list of available fuelPriceHistorys",response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
	@Override
	public Iterable<FuelPriceHistoryItem> list() {
        Iterable<FuelPriceHistoryItem> listAllFuelPriceHistoryItems = this.fuelPriceHistoryItemService.listAllFuelPriceHistoryItems();
        return listAllFuelPriceHistoryItems;
	}
	

	@ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Search a fuelPriceHistory with an ID",response = FuelPriceHistory.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")   
	@Override
	public FuelPriceHistoryItem getById(@PathVariable Integer id) {
		FuelPriceHistoryItem fuelPriceHistoryItem = this.fuelPriceHistoryItemService.getFuelPriceHistoryItemById(id);
        return fuelPriceHistoryItem;
	}

	@ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Add a FuelPriceHistoryItem")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	@Override
	public FuelPriceHistoryItem add(@RequestBody FuelPriceHistoryItem domainObject) {
		return this.fuelPriceHistoryItemService.saveFuelPriceHistoryItem(domainObject);
	}

	@ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Update a FuelPriceHistoryItem")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
	@Override
	public FuelPriceHistoryItem update(@PathVariable Integer id, @RequestBody FuelPriceHistoryItem fuelPriceHistoryItem) {
    	FuelPriceHistoryItem storedFuelPriceHistoryItem = this.fuelPriceHistoryItemService.getFuelPriceHistoryItemById(id);
    	storedFuelPriceHistoryItem.setFuelPriceHistoryItem(fuelPriceHistoryItem);
    	this.fuelPriceHistoryItemService.saveFuelPriceHistoryItem(storedFuelPriceHistoryItem);
        return this.fuelPriceHistoryItemService.getFuelPriceHistoryItemById(id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Delete a fuelPriceHistory")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Override
	public ResponseEntity delete(@PathVariable Integer id) {
    	this.fuelPriceHistoryItemService.deleteFuelPriceHistoryItem(id);
        return new ResponseEntity(HttpStatus.OK);
	}

	@ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "View an AVG of Sales Price by City Name",response = Double.class)
    @RequestMapping(value = "/avg/salespricebycity/{cityName}", method= RequestMethod.GET, produces = "application/json")   
	@Override
	public Double getAvgSalesPriceByCity(@PathVariable String cityName) {
		return this.fuelPriceHistoryItemService.getAvgSalesPriceByCity(cityName);
	}

    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "View a list AVG of sales Price and purchaseAmount with an Banner",response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/avg/salespriceandpurchaseamountbybanner", method= RequestMethod.GET, produces = "application/json")
	@Override
	public List<AVGSalesPriceAndPurchaseAmountByBanner> getListAVGSalePriceAndPurchaseAmountByBanner() {
		return this.fuelPriceHistoryItemService.getListAVGSalePriceAndPurchaseAmountByBanner();
	}

    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "View a list AVG of sales Price and purchaseAmount with an city",response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/avg/salespriceandpurchaseamountbycity", method= RequestMethod.GET, produces = "application/json")
	@Override
	public List<AVGSalesPriceAndPurchaseAmountByCity> getListAVGSalePriceAndPurchaseAmountByCity() {
    	return this.fuelPriceHistoryItemService.getListAVGSalePriceAndPurchaseAmountByCity();
	}
	
}
