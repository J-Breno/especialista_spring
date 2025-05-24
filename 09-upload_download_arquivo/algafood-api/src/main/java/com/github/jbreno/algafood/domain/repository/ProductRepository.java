package com.github.jbreno.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.jbreno.algafood.domain.model.PhotoProduct;
import com.github.jbreno.algafood.domain.model.Product;
import com.github.jbreno.algafood.domain.model.Restaurant;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQueries{
	List<Product> findAllByRestaurantId(Long restaurantId);

	Optional<Product> findByIdAndRestaurantId(Long productId, Long restaurantId);
    
	List<Product> findAllByRestaurant(Restaurant restaurant);
    
	@Query("from Product p where p.active = true and p.restaurant = :restaurant")
	List<Product> findActiveByRestaurant(Restaurant restaurant);
    
    @Query("select f from PhotoProduct f join f.product p where p.restaurant.id = :restaurantId and f.product.id = :productId")
    Optional<PhotoProduct> findPhotoById(Long restaurantId, Long productId);
}
