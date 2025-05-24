package com.github.jbreno.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.jbreno.algafood.domain.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
		List<Restaurant> findByShippingFeeBetween(BigDecimal initialFee, BigDecimal finalFee);

		@Query("FROM Restaurant WHERE name LIKE %:name% AND kitchen.id = :id")
		List<Restaurant> searchByName(String name, @Param("id") Long kitchen);
//		List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchen);
		
		Optional<Restaurant> findFirstRestaurantByNameContaining(String name);
		
		List<Restaurant> findTop2ByNameContaining(String name);
		
		int countByKitchenId(Long id);
}
