package br.com.indra.avaliacao.apirest.models.fuelpricehistory.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.indra.avaliacao.apirest.models.fuelpricehistory.controller.IFuelPriceHistoryController;
import br.com.indra.avaliacao.apirest.models.fuelpricehistory.entity.FuelPriceHistory;
import br.com.indra.avaliacao.apirest.models.fuelpricehistory.services.IFuelPriceHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/v1/fuelpricehistory")
@Api(value="1", description="Operations pertaining to FuelPriceHistory in Online Store")
public class FuelPriceHistoryController implements IFuelPriceHistoryController {
	
	private IFuelPriceHistoryService fuelPriceHistoryService;
	

	@Autowired
    public void setFuelPriceHistoryService(IFuelPriceHistoryService fuelPriceHistoryService) {
		this.fuelPriceHistoryService = fuelPriceHistoryService;
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
	public Iterable<FuelPriceHistory> list() {
        Iterable<FuelPriceHistory> listAllFuelPriceHistorys = this.fuelPriceHistoryService.listAllFuelPriceHistories();
        return listAllFuelPriceHistorys;
	}
	

	@ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Search a fuelPriceHistory with an ID",response = FuelPriceHistory.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")   
	@Override
	public FuelPriceHistory getById(@PathVariable Integer id) {
    	FuelPriceHistory fuelPriceHistory = this.fuelPriceHistoryService.getFuelPriceHistoryById(id);
        return fuelPriceHistory;
	}

	
	@ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Add a FuelPriceHistory")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	@Override
	public FuelPriceHistory add(@RequestBody FuelPriceHistory domainObject) {
		return this.fuelPriceHistoryService.saveFuelPriceHistory(domainObject);
	}

	@ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Update a fuelPriceHistory")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
	@Override
	public FuelPriceHistory update(@PathVariable Integer id, @RequestBody FuelPriceHistory fuelPriceHistory) {
    	FuelPriceHistory storedFuelPriceHistory = this.fuelPriceHistoryService.getFuelPriceHistoryById(id);
    	storedFuelPriceHistory.setFuelPriceHistory(fuelPriceHistory);
    	this.fuelPriceHistoryService.saveFuelPriceHistory(storedFuelPriceHistory);
        return this.fuelPriceHistoryService.getFuelPriceHistoryById(id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Delete a fuelPriceHistory")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Override
	public ResponseEntity delete(@PathVariable Integer id) {
    	this.fuelPriceHistoryService.deleteFuelPriceHistory(id);
        return new ResponseEntity(HttpStatus.OK);
	}


	

}
