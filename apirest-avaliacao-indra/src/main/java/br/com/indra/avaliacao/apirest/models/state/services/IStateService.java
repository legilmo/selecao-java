package br.com.indra.avaliacao.apirest.models.state.services;


import br.com.indra.avaliacao.apirest.models.state.entity.State;

public interface IStateService {
    Iterable<State> listAllStates();

    State getStateById(Integer id);
    
    State getStateByInitials(String initials);

    State saveState(State state);

    void deleteState(Integer id);

}
