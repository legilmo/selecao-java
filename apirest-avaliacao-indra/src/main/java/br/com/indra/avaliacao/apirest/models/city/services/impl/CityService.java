package br.com.indra.avaliacao.apirest.models.city.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.indra.avaliacao.apirest.models.city.entity.City;
import br.com.indra.avaliacao.apirest.models.city.repository.CityRepository;
import br.com.indra.avaliacao.apirest.models.city.services.ICityService;


@Service
public class CityService implements ICityService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private CityRepository cityRepository;
    
    @Autowired
    public void setCityRepository(CityRepository cityRepository ) {
        this.cityRepository = cityRepository;
    }
    
	@Override
	public Iterable<City> listAllCities() {
        logger.debug("CityService::listAllCitys called");
        Iterable<City> cities = cityRepository.findAll();
  
        return cities;
	}

	@Override
	public City getCityById(Integer id) {
        logger.debug("CityService::getCityById called");
        Optional<City> City = cityRepository.findById(id);
        
        if(City.isPresent()) {
        	City r = City.get();
        	return r;
        } else {
        	return null;
        }
		
	}

	@Override
	public City saveCity(City City) {
        logger.debug("CityService::saveCity called");
		
        return cityRepository.save(City);
	}

	@Override
	public void deleteCity(Integer id) {
        logger.debug("CityService::deleteCity called");        
        cityRepository.deleteById(id);
	}

	@Override
	public City getCityByName(String name) {
        logger.debug("CityService::getCityByName called");
        return cityRepository.findByName(name);
        
	}



	

}
