package br.com.indra.avaliacao.apirest.models.distributor.services;

import br.com.indra.avaliacao.apirest.models.distributor.entity.Distributor;


public interface IDistributorService {
    Iterable<Distributor> listAllDistributors();

    Distributor getDistributorById(Integer id);
    
    Distributor getDistributorByCNPJ(String name);

    Distributor saveDistributor(Distributor distributor);

    void deleteDistributor(Integer id);

}
