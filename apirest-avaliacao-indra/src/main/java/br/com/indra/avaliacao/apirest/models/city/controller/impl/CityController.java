package br.com.indra.avaliacao.apirest.models.city.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.indra.avaliacao.apirest.models.city.controller.ICityController;
import br.com.indra.avaliacao.apirest.models.city.entity.City;
import br.com.indra.avaliacao.apirest.models.city.services.ICityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;


@RestController
@RequestMapping("/v1/city")
@Api( value="1", description="Operations pertaining to citys in CO", consumes = "Citiesss")
public class CityController implements ICityController {
	
	
    private ICityService cityService;

    @Autowired
    public void setCityService(ICityService cityService) {
        this.cityService = cityService;
    }
    
   @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "View a list of Cities. Done.",response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
	@Override
	public Iterable<City> list() {
	   Iterable<City> cityList = cityService.listAllCities();
        System.out.println("CityController::list():List Size:"+cityList.toString());
        
        return cityList;
	}

    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Search a City with an ID",response = City.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")   
	@Override
	public City getById(@PathVariable Integer id) {
    	City City = this.cityService.getCityById(id);
        return City;
	}
	
	@ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Add a City")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	@Override
	public City add(@RequestBody  City domainObject) {
		System.out.println(domainObject.toString());
		return cityService.saveCity(domainObject);
	}

    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Update a City")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
	@Override
	public City update(@PathVariable Integer id, @RequestBody City City) {
    	City storedCity = this.cityService.getCityById(id);
    	storedCity.setCity(City);

    	this.cityService.saveCity(storedCity);
        return this.cityService.saveCity(storedCity);
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Delete a City")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Override
	public ResponseEntity delete(@PathVariable Integer id) {
    	cityService.deleteCity(id);
        return new ResponseEntity(HttpStatus.OK);
	}

}
