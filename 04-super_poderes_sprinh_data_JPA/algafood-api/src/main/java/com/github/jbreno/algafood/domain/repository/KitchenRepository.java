package com.github.jbreno.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.jbreno.algafood.domain.model.Kitchen;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long>{
//	List<Kitchen> searchByName(String name);
	
}
