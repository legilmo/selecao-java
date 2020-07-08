package br.com.indra.avaliacao.apirest.models.banner.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.indra.avaliacao.apirest.models.banner.entity.Banner;

public interface BannerRepository extends CrudRepository<Banner, Integer> {

	@Query(value = "SELECT ban FROM Banner ban " +
            "WHERE ban.name = :name ")
	public Banner findByName(@Param("name") String name);
}
