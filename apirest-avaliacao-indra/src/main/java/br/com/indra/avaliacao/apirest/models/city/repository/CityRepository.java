package br.com.indra.avaliacao.apirest.models.city.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.indra.avaliacao.apirest.models.city.entity.City;

public interface CityRepository extends CrudRepository<City, Integer> {
	@Query(value = "SELECT cid FROM City cid " +
            "WHERE cid.name = :name ")
	public City findByName(@Param("name") String name);
}
