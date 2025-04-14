package com.github.jbreno.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.repository.KitchenRepository;

@Service
public class KitchenRegistrationService {
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}
	
	
}
