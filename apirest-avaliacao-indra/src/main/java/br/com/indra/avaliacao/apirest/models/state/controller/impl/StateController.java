package br.com.indra.avaliacao.apirest.models.state.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.indra.avaliacao.apirest.models.state.controller.IStateController;
import br.com.indra.avaliacao.apirest.models.state.entity.State;
import br.com.indra.avaliacao.apirest.models.state.services.IStateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;



@RestController
@RequestMapping("/v1/state")
@Api( value="DF", description="Operations pertaining to states in CO", consumes = "Stateesss")
public class StateController implements IStateController {
	
	
    private IStateService stateService;

    @Autowired
    public void setStateService(IStateService stateService) {
        this.stateService = stateService;
    }
    
    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "View a list of available States",response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
	@Override
	public Iterable<State> list() {
        Iterable<State> stateList = stateService.listAllStates();
        System.out.println("StateController::list():List Size:"+stateList.toString());
        
        return stateList;
	}

    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Search a State with an ID",response = State.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")   
	@Override
	public State getById(@PathVariable Integer id) {
    	State State = this.stateService.getStateById(id);
        return State;
	}
	
	@ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Add a State")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	@Override
	public State add(@RequestBody  State domainObject) {
		System.out.println(domainObject.toString());
		return stateService.saveState(domainObject);
	}

    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Update a State")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
	@Override
	public State update(@PathVariable Integer id, @RequestBody State State) {
    	State storedState = this.stateService.getStateById(id);
    	storedState.setState(State);

    	this.stateService.saveState(storedState);
        return this.stateService.saveState(storedState);
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ApiOperation(authorizations = {@Authorization(value = "Bearer")},value = "Delete a State")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Override
	public ResponseEntity delete(@PathVariable Integer id) {
    	stateService.deleteState(id);
        return new ResponseEntity(HttpStatus.OK);
	}

}
