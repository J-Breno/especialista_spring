package com.github.jbreno.algafood.domain.repository;

import java.util.List;

import com.github.jbreno.algafood.domain.model.State;

public interface StateRepository {
	List<State> all();
	State search(Long id);
	State save(State state);
	void remove(State state);
}
