package br.com.indra.avaliacao.apirest.models.product.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.indra.avaliacao.apirest.models.product.controller.IProductController;
import br.com.indra.avaliacao.apirest.models.product.entity.Product;
import br.com.indra.avaliacao.apirest.models.product.services.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/product")
@Api(value="onlinestore", description="Operations pertaining to products in Online Store")
public class ProductController implements IProductController {
	
	private IProductService productService;


	@Autowired
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	




    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "View a list of available products",response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
	@Override
	public Iterable<Product> list() {
        Iterable<Product> listAllProducts = this.productService.listAllProducts();
        System.out.print("Lista de protodos");

        return listAllProducts;
	}

    
	@ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Search a Product with an ID",response = Product.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")   
	@Override
	public Product getById(@PathVariable Integer id) {
		Product product = this.productService.getProductById(id);
        return product;
	}

	
    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Add a product")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	@Override
	public Product add(@RequestBody Product domainObject) {
		return this.productService.saveProduct(domainObject);
	}

    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Update a product")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
	@Override
	public Product update(@PathVariable Integer id, @RequestBody Product domainObject) {
    	Product storedProduct = this.productService.getProductById(id);
  //  	storedProduct.setCategories(new HashSet<>());
    	this.productService.saveProduct(storedProduct);
    	
//    	Iterator<Category> categoryIt = domainObject.getCategories().iterator();
//    	while(categoryIt.hasNext()) {
//    		Category c = categoryIt.next();
//    		storedProduct.getCategories().add((this.categoryService.getCategoryById(c.getId())));
//    		System.out.println("CagetegoryID:"+ c.getId());
//    	}
//    	
    	storedProduct.setProduct(domainObject);
    	
    	this.productService.saveProduct(storedProduct);
        return this.productService.getProductById(id);
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Delete a product")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Override
	public ResponseEntity delete(@PathVariable Integer id) {
    	this.productService.deleteProduct(id);
    	System.out.println("Producto "+id+" deletado com sucesso");
        return new ResponseEntity(HttpStatus.OK);
	}

}
