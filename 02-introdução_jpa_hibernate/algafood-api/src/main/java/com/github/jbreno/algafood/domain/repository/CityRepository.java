package com.github.jbreno.algafood.domain.repository;

import java.util.List;

import com.github.jbreno.algafood.domain.model.City;

public interface CityRepository {
	List<City> all();
	City search(Long id);
	City save(City city);
	void remove(City city);
}
