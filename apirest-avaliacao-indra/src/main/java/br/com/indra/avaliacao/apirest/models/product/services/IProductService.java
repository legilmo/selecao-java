package br.com.indra.avaliacao.apirest.models.product.services;

import br.com.indra.avaliacao.apirest.models.product.entity.Product;

public interface IProductService {
    Iterable<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);
}
