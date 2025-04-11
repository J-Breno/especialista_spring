package com.github.jbreno.algafood.domain.repository;

import java.util.List;

import com.github.jbreno.algafood.domain.model.Restaurant;

public interface RestaurantRepository {
	List<Restaurant> all();
	Restaurant search(Long id);
	Restaurant save(Restaurant restaurant);
	void remove(Restaurant restaurant);
}
