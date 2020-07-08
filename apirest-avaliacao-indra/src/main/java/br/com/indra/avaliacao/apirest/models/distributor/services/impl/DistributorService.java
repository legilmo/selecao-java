package br.com.indra.avaliacao.apirest.models.distributor.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.indra.avaliacao.apirest.models.distributor.entity.Distributor;
import br.com.indra.avaliacao.apirest.models.distributor.repository.DistributorRepository;
import br.com.indra.avaliacao.apirest.models.distributor.services.IDistributorService;


@Service
public class DistributorService implements IDistributorService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private DistributorRepository distributorRepository;
    
    @Autowired
    public void setDistributorRepository(DistributorRepository distributorRepository ) {
        this.distributorRepository = distributorRepository;
    }
    
	@Override
	public Iterable<Distributor> listAllDistributors() {
        logger.debug("DistributorService::listAllDistributors called");
        Iterable<Distributor> distributors = distributorRepository.findAll();
  
        return distributors;
	}

	@Override
	public Distributor getDistributorById(Integer id) {
        logger.debug("DistributorService::getDistributorById called");
        Optional<Distributor> Distributor = distributorRepository.findById(id);
        
        if(Distributor.isPresent()) {
        	Distributor r = Distributor.get();
        	return r;
        } else {
        	return null;
        }
		
	}

	@Override
	public Distributor saveDistributor(Distributor distributor) {
        logger.debug("DistributorService::saveDistributor called");
        return distributorRepository.save(distributor);
	}

	@Override
	public void deleteDistributor(Integer id) {
        logger.debug("DistributorService::deleteDistributor called");        
        distributorRepository.deleteById(id);
	}

	@Override
	public Distributor getDistributorByCNPJ(String cnpj) {
     logger.debug("DistributorService::getDistributorByName called");
        return distributorRepository.findByCNPJ(cnpj);
        
	}



	

}
