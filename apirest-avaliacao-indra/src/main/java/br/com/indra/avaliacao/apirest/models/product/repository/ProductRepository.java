package br.com.indra.avaliacao.apirest.models.product.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.indra.avaliacao.apirest.models.product.entity.Product;

@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Integer>{
	@Query(value = "SELECT prod FROM Product prod " +
                   "WHERE prod.name = :name ")
	public Product findByName(@Param("name") String name);
}
