package br.com.indra.avaliacao.apirest.models.region.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.indra.avaliacao.apirest.models.region.entity.Region;

public interface RegionRepository extends CrudRepository<Region, Integer> {
	@Query(value = "SELECT reg FROM Region reg " +
            "WHERE reg.initials = :initials ")
    public Region getRegionByInitials(@Param("initials") String initials);
}
