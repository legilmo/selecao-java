package br.com.indra.avaliacao.apirest.models.city.services;

import br.com.indra.avaliacao.apirest.models.city.entity.City;


public interface ICityService {
    Iterable<City> listAllCities();

    City getCityById(Integer id);
    
    City getCityByName(String name);

    City saveCity(City city);

    void deleteCity(Integer id);

}
