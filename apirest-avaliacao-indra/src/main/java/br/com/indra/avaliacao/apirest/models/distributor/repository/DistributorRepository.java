package br.com.indra.avaliacao.apirest.models.distributor.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.indra.avaliacao.apirest.models.distributor.entity.Distributor;

public interface DistributorRepository extends CrudRepository<Distributor, Integer> {
	
	@Query(value = "SELECT dis FROM Distributor dis " +
            "WHERE dis.cnpj = :cnpj ")
	public Distributor findByCNPJ(@Param("cnpj") String cnpj);
}
