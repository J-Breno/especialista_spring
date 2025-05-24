package com.github.jbreno.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.exception.ProductNotFoundException;
import com.github.jbreno.algafood.domain.model.Product;
import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.repository.ProductRepository;
import com.github.jbreno.algafood.domain.repository.RestaurantRepository;

@Service
public class ProductRegistrationService {


	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RestaurantRegistrationService restaurantService;

	public List<Product> list(Long restaurantId) {
		return productRepository.findAllByRestaurantId(restaurantId);
	}

	public Product search(Long restaurantId, Long productId) {
		return searchOrFail(restaurantId, productId);
	}

	@Transactional
	public Product save(Long restaurantId, Product product) {
		product = productRepository.save(product);

		Restaurant restaurant = restaurantService.searchOrFail(restaurantId);

		restaurant.getProducts().add(product);

		restaurantRepository.save(restaurant);

		return product;
	}

	@Transactional
	public void removeFromRestaurant(Long restaurantId, Long productId) {
		Restaurant restaurant = restaurantService.searchOrFail(restaurantId);
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException(productId));

		restaurant.getProducts().remove(product);

		restaurantRepository.save(restaurant);
	}



	

	public Product searchOrFail(Long restaurantId, Long productId) {
		return productRepository.findByIdAndRestaurantId(productId, restaurantId)
				.orElseThrow(() -> new ProductNotFoundException(
						String.format("Produto com ID %d não encontrado no restaurante %d", productId, restaurantId)));
	}
}
