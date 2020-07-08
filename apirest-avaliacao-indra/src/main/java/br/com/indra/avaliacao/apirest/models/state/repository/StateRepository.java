package br.com.indra.avaliacao.apirest.models.state.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.indra.avaliacao.apirest.models.state.entity.State;

public interface StateRepository extends CrudRepository<State, Integer> {

	@Query(value = "SELECT st FROM State st " +
            "WHERE st.initials = :initials ")
    public State getStateByInitials(@Param("initials") String initials);
}
