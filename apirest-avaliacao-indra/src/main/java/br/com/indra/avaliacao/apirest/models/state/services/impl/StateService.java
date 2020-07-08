package br.com.indra.avaliacao.apirest.models.state.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.indra.avaliacao.apirest.models.state.entity.State;
import br.com.indra.avaliacao.apirest.models.state.repository.StateRepository;
import br.com.indra.avaliacao.apirest.models.state.services.IStateService;


@Service
public class StateService implements IStateService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private StateRepository stateRepository;
    
    @Autowired
    public void setStateRepository(StateRepository stateRepository ) {
        this.stateRepository = stateRepository;
    }
    
	@Override
	public Iterable<State> listAllStates() {
        logger.debug("StateService::listAllStates called");
        Iterable<State> states = stateRepository.findAll();
  
        return states;
	}

	@Override
	public State getStateById(Integer id) {
        logger.debug("StateService::getStateById called");
        Optional<State> State = stateRepository.findById(id);
        
        if(State.isPresent()) {
        	State r = State.get();
        	return r;
        } else {
        	return null;
        }
		
	}

	@Override
	public State saveState(State State) {
        logger.debug("StateService::saveState called");
        return stateRepository.save(State);
	}

	@Override
	public void deleteState(Integer id) {
        logger.debug("StateService::deleteState called");        
        stateRepository.deleteById(id);
	}

	@Override
	public State getStateByInitials(String initials) {
     logger.debug("StateService::getStateByName called");
        return stateRepository.getStateByInitials(initials);
  
	}

}
