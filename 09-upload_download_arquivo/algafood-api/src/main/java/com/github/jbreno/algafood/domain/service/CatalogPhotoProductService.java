package com.github.jbreno.algafood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.model.PhotoProduct;
import com.github.jbreno.algafood.domain.repository.ProductRepository;
import com.github.jbreno.algafood.domain.service.PhotoStorageService.NewPhoto;

@Service
public class CatalogPhotoProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PhotoStorageService photoStorageService;
	
	@Transactional
	public PhotoProduct save(PhotoProduct photo, InputStream dataFile) {
		Long restaurantId = photo.getRestaurantId();
		Long  productId = photo.getProduct().getId();
		String nameNewFile = photoStorageService.genareteNameFile(photo.getFileName());
		String nameFileExists = null;
		
		Optional<PhotoProduct> photoExists = productRepository.findPhotoById(restaurantId, productId);
		
		if(photoExists.isPresent()) {
			nameFileExists = photoExists.get().getFileName();
			productRepository.delete(photoExists.get());
		}
		
		photo.setFileName(nameNewFile);
		photo = productRepository.save(photo);
		productRepository.flush();
		
		NewPhoto newPhoto = NewPhoto.builder()
				.nameFile(photo.getFileName())
				.inputStream(dataFile)
				.build();
		
		photoStorageService.toReplace(nameFileExists, newPhoto);
		
		return photo;
	}
}
