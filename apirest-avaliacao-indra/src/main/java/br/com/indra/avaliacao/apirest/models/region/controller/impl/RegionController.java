package br.com.indra.avaliacao.apirest.models.region.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.indra.avaliacao.apirest.models.region.controller.IRegionController;
import br.com.indra.avaliacao.apirest.models.region.entity.Region;
import br.com.indra.avaliacao.apirest.models.region.services.IRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;



@RestController
@RequestMapping("/v1/region")
@Api( value="CO", description="Operations pertaining to regions in CO", consumes = "Regionesss")
public class RegionController implements IRegionController {
	
	
    private IRegionService regionService;

    @Autowired
    public void setRegionService(IRegionService regionService) {
        this.regionService = regionService;
    }
    
    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "View a list of available Regions",response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
	@Override
	public Iterable<Region> list() {
        Iterable<Region> regionList = regionService.listAllRegions();
        System.out.println("RegionController::list():List Size:"+regionList.toString());
        
        return regionList;
	}

    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Search a Region with an ID",response = Region.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")   
	@Override
	public Region getById(@PathVariable Integer id) {
    	Region Region = this.regionService.getRegionById(id);
        return Region;
	}
	
	@ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Add a Region")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	@Override
	public Region add(@RequestBody  Region domainObject) {
		System.out.println(domainObject.toString());
		return regionService.saveRegion(domainObject);
	}

    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Update a Region")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
	@Override
	public Region update(@PathVariable Integer id, @RequestBody Region Region) {
    	Region storedRegion = this.regionService.getRegionById(id);
    	storedRegion.setRegion(Region);

    	this.regionService.saveRegion(storedRegion);
        return this.regionService.saveRegion(storedRegion);
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Delete a Region")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Override
	public ResponseEntity delete(@PathVariable Integer id) {
    	regionService.deleteRegion(id);
        return new ResponseEntity(HttpStatus.OK);
	}

}
