package br.com.indra.avaliacao.apirest.models.product.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.indra.avaliacao.apirest.models.product.entity.Product;
import br.com.indra.avaliacao.apirest.models.product.repository.ProductRepository;
import br.com.indra.avaliacao.apirest.models.product.services.IProductService;

@Service
public class ProductService implements IProductService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> listAllProducts() {
    	Iterable<Product> products = productRepository.findAll();
        logger.debug("ProductService::listAllProducts called");
       
        return products;
    }

    @Override
    public Product getProductById(Integer id) {
        logger.debug("ProductService::getProductById called");
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
        	Product p = product.get();
      //  	p.setPicture(ImageUtils.getBase64Image(p.getPictureName(), p.getPictureExtension()));
        	return p;
        } else {
        	return null;
        }
    }

    @Override
    public Product saveProduct(Product product) {
        logger.debug("ProductService::saveProduct called");
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        logger.debug("ProductService::deleteProduct called");        
        productRepository.deleteById(id);
    }
}