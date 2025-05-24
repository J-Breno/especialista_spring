package com.github.jbreno.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.jbreno.algafood.domain.model.Product;
import com.github.jbreno.algafood.domain.model.Restaurant;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQueries{
	@Query("SELECT p FROM Product p JOIN p.restaurants r WHERE r.id = :restaurantId")
    List<Product> findAllByRestaurantId(@Param("restaurantId") Long restaurantId);

    @Query("SELECT p FROM Product p JOIN p.restaurants r WHERE p.id = :productId AND r.id = :restaurantId")
    Optional<Product> findByIdAndRestaurantId(
        @Param("productId") Long productId,
        @Param("restaurantId") Long restaurantId
    );
    
    List<Product> findAllByRestaurants(Restaurant restaurant);
    
    @Query("from Product p WHERE p.active = true AND p.restaurants = :restaurant")
    List<Product> findActiveByRestaurant(Restaurant restaurant);
}
