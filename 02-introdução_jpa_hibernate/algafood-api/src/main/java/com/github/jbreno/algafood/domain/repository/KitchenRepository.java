package com.github.jbreno.algafood.domain.repository;

import java.util.List;

import com.github.jbreno.algafood.domain.model.Kitchen;

public interface KitchenRepository {
	List<Kitchen> all();
	Kitchen search(Long id);
	Kitchen save(Kitchen kitchen);
	void remove(Long id);
}
