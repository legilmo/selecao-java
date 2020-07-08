package br.com.indra.avaliacao.apirest.models.region.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.indra.avaliacao.apirest.models.region.entity.Region;
import br.com.indra.avaliacao.apirest.models.region.repository.RegionRepository;
import br.com.indra.avaliacao.apirest.models.region.services.IRegionService;


@Service
public class RegionService implements IRegionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private RegionRepository regionRepository;
    
    @Autowired
    public void setRegionRepository(RegionRepository regionRepository ) {
        this.regionRepository = regionRepository;
    }
    
	@Override
	public Iterable<Region> listAllRegions() {
        logger.debug("RegionService::listAllRegions called");
        Iterable<Region> regions = regionRepository.findAll();
  
        return regions;
	}

	@Override
	public Region getRegionById(Integer id) {
        logger.debug("RegionService::getRegionById called");
        Optional<Region> region = regionRepository.findById(id);
        
        if(region.isPresent()) {
        	Region r = region.get();
        	return r;
        } else {
        	return null;
        }
		
	}

	@Override
	public Region saveRegion(Region region) {
        logger.debug("RegionService::saveRegion called");

        return regionRepository.save(region);
	}

	@Override
	public void deleteRegion(Integer id) {
        logger.debug("RegionService::deleteRegion called");        
        regionRepository.deleteById(id);
	}



}
