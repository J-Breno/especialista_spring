package com.github.jbreno.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.model.PhotoProduct;
import com.github.jbreno.algafood.domain.repository.ProductRepository;

@Service
public class CatalogPhotoProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public PhotoProduct save(PhotoProduct photo) {
		Long restaurantId = photo.getRestaurantId();
		Long  productId = photo.getProduct().getId();
		Optional<PhotoProduct> photoExists = productRepository.findPhotoById(restaurantId, productId);
		
		if(photoExists.isPresent()) {
			productRepository.delete(photoExists.get());
		}
		
		return productRepository.save(photo);
	}
}
