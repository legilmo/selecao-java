package br.com.indra.avaliacao.apirest.models.distributor.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.indra.avaliacao.apirest.models.distributor.controller.IDistributorController;
import br.com.indra.avaliacao.apirest.models.distributor.entity.Distributor;
import br.com.indra.avaliacao.apirest.models.distributor.services.IDistributorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;



@RestController
@RequestMapping("/v1/distributor")
@Api( value="1", description="Operations pertaining to distributors in CO", consumes = "Citiesss")
public class DistributorController implements IDistributorController {
	
    private IDistributorService distributorService;

    @Autowired
    public void setDistributorService(IDistributorService distributorService) {
        this.distributorService = distributorService;
    }
    
    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "View a list of available Cities",response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
	@Override
	public Iterable<Distributor> list() {
        Iterable<Distributor> distributorList = distributorService.listAllDistributors();
        System.out.println("DistributorController::list():List Size:"+distributorList.toString());
        
        return distributorList;
	}

    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Search a Distributor with an ID",response = Distributor.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")   
	@Override
	public Distributor getById(@PathVariable Integer id) {
    	Distributor Distributor = this.distributorService.getDistributorById(id);
        return Distributor;
	}
	
	@ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Add a Distributor")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	@Override
	public Distributor add(@RequestBody  Distributor domainObject) {
		System.out.println(domainObject.toString());
		return distributorService.saveDistributor(domainObject);
	}

    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Update a Distributor")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
	@Override
	public Distributor update(@PathVariable Integer id, @RequestBody Distributor Distributor) {
    	Distributor storedDistributor = this.distributorService.getDistributorById(id);
    	storedDistributor.setDistributor(Distributor);

    	this.distributorService.saveDistributor(storedDistributor);
        return this.distributorService.saveDistributor(storedDistributor);
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Delete a Distributor")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Override
	public ResponseEntity delete(@PathVariable Integer id) {
    	distributorService.deleteDistributor(id);
        return new ResponseEntity(HttpStatus.OK);
	}

}
