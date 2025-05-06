package com.github.jbreno.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.ProductNotFoundException;
import com.github.jbreno.algafood.domain.exception.RestaurantNotFoundException;
import com.github.jbreno.algafood.domain.model.Product;
import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.repository.ProductRepository;
import com.github.jbreno.algafood.domain.repository.RestaurantRepository;

@Service
public class ProductRegistrationService {

	private static final String MSG_PRODUCT_IN_USE = "Produto de código %d não pode ser removida, pois está em uso";

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

	@Transactional
	public Product update(Long restaurantId, Long id, Product updatedProduct) {
		Restaurant restaurant = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new RestaurantNotFoundException("Restaurante não encontrado"));

		Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

		if (!existingProduct.getRestaurants().contains(restaurant)) {
			throw new BusinessException("Produto não pertence a este restaurante");
		}

		existingProduct.setName(updatedProduct.getName());
		existingProduct.setPrice(updatedProduct.getPrice());
		existingProduct.setActive(updatedProduct.getActive());
		existingProduct.setDescription(updatedProduct.getDescription());

		return productRepository.save(existingProduct);
	}

	@Transactional
	public void remove(Long restaurantId, Long productId) {
		  try {
		        Restaurant restaurant = restaurantRepository.findById(restaurantId)
		                .orElseThrow(() -> new RestaurantNotFoundException("Restaurante não encontrado"));
		        
		        Product product = productRepository.findById(productId)
		                .orElseThrow(() -> new ProductNotFoundException(productId));

		        if (!restaurant.getProducts().contains(product)) {
		            throw new BusinessException("Produto não está associado a este restaurante.");
		        }

		        restaurant.getProducts().remove(product);
		        product.getRestaurants().remove(restaurant);
		        
		        restaurantRepository.saveAndFlush(restaurant);
		        productRepository.saveAndFlush(product);
		        
		    } catch (EmptyResultDataAccessException e) {
		        throw new ProductNotFoundException(productId);
		    } catch (DataIntegrityViolationException e) {
		        throw new EntityInUseException(String.format(MSG_PRODUCT_IN_USE, productId));
		    }
	}

	public Product searchOrFail(Long restaurantId, Long productId) {
		return productRepository.findByIdAndRestaurantId(productId, restaurantId)
				.orElseThrow(() -> new ProductNotFoundException(
						String.format("Produto com ID %d não encontrado no restaurante %d", productId, restaurantId)));
	}
}
