package br.com.indra.avaliacao.apirest.models.region.services;

import br.com.indra.avaliacao.apirest.models.region.entity.Region;

public interface IRegionService {
    Iterable<Region> listAllRegions();

    Region getRegionById(Integer id);

    Region saveRegion(Region region);

    void deleteRegion(Integer id);

}
