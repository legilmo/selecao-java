package br.com.indra.avaliacao.apirest.models.banner.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.indra.avaliacao.apirest.models.banner.controller.IBannerController;
import br.com.indra.avaliacao.apirest.models.banner.entity.Banner;
import br.com.indra.avaliacao.apirest.models.banner.services.IBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;



@RestController
@RequestMapping("/v1/banner")
@Api( value="1", description="Operations pertaining to banners in CO", consumes = "Banneresss")
public class BannerController implements IBannerController {
	
	
    private IBannerService bannerService;

    @Autowired
    public void setBannerService(IBannerService bannerService) {
        this.bannerService = bannerService;
    }
    
    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "View a list of available Banners",response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
	@Override
	public Iterable<Banner> list() {
        Iterable<Banner> bannerList = bannerService.listAllBanners();
        System.out.println("BannerController::list():List Size:"+bannerList.toString());
        
        return bannerList;
	}

    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Search a Banner with an ID",response = Banner.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")   
	@Override
	public Banner getById(@PathVariable Integer id) {
    	Banner Banner = this.bannerService.getBannerById(id);
        return Banner;
	}
	
	@ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Add a Banner")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	@Override
	public Banner add(@RequestBody  Banner domainObject) {
		System.out.println(domainObject.toString());
		return bannerService.saveBanner(domainObject);
	}

    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Update a Banner")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
	@Override
	public Banner update(@PathVariable Integer id, @RequestBody Banner Banner) {
    	Banner storedBanner = this.bannerService.getBannerById(id);
    	storedBanner.setBanner(Banner);

    	this.bannerService.saveBanner(storedBanner);
        return this.bannerService.saveBanner(storedBanner);
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Delete a Banner")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Override
	public ResponseEntity delete(@PathVariable Integer id) {
    	bannerService.deleteBanner(id);
        return new ResponseEntity(HttpStatus.OK);
	}

}
