package com.github.jbreno.algafood.api.controller;

import java.io.InputStream;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.jbreno.algafood.api.assembler.PhotoProductDTOAssembler;
import com.github.jbreno.algafood.api.model.PhotoProductDTO;
import com.github.jbreno.algafood.api.model.input.PhotoProductInput;
import com.github.jbreno.algafood.domain.exception.EntityNotFoundException;
import com.github.jbreno.algafood.domain.model.PhotoProduct;
import com.github.jbreno.algafood.domain.model.Product;
import com.github.jbreno.algafood.domain.service.CatalogPhotoProductService;
import com.github.jbreno.algafood.domain.service.PhotoStorageService;
import com.github.jbreno.algafood.domain.service.ProductRegistrationService;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

	@Autowired
	private ProductRegistrationService productService;

	@Autowired
	private CatalogPhotoProductService service;

	@Autowired
	private PhotoStorageService phtoPhotoStorageService;

	@Autowired
	private PhotoProductDTOAssembler photoProductDTOAssembler;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public PhotoProductDTO updatePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
			@Valid PhotoProductInput photoProductInput) throws Exception {
		Product product = productService.searchOrFail(restaurantId, productId);
		PhotoProduct photo = new PhotoProduct();
		MultipartFile file = photoProductInput.getFile();

		photo.setProduct(product);
		photo.setDescription(photoProductInput.getDescription());
		photo.setContentType(file.getContentType());
		photo.setSize(file.getSize());
		photo.setFileName(file.getOriginalFilename());

		PhotoProduct photoSave = service.save(photo, file.getInputStream());

		return photoProductDTOAssembler.toModel(photoSave);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public PhotoProductDTO search(@PathVariable Long restaurantId, @PathVariable Long productId) {
		PhotoProduct photoProduct = service.searchOrFeiel(restaurantId, productId);

		return photoProductDTOAssembler.toModel(photoProduct);
	}

	@GetMapping
    public ResponseEntity<InputStreamResource> servePhoto(@PathVariable Long restaurantId, 
            @PathVariable Long productId,@RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException { 
    	try {
    		 PhotoProduct photoProduct = service.searchOrFeiel(restaurantId, productId);
    		 
    		 MediaType mediaTypePhoto = MediaType.parseMediaType(photoProduct.getContentType());
    		 List<MediaType> mediaTypeAccept = MediaType.parseMediaTypes(acceptHeader);
    		 
    		 checkCompatibilityMediaType(mediaTypePhoto, mediaTypeAccept);
    	        
    	     InputStream inputStream = phtoPhotoStorageService.recover(photoProduct.getFileName());
    	        
    	     return ResponseEntity
    	    		 .ok()
    	        	 .contentType(MediaType.IMAGE_JPEG)
    	       		 .body(new InputStreamResource(inputStream));
    	} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
       
    }
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long restaurantId, 
	        @PathVariable Long productId) {
		service.delete(restaurantId, productId);
	}   

	private void checkCompatibilityMediaType(MediaType mediaTypePhoto, List<MediaType> mediaTypeAccepts) throws HttpMediaTypeNotAcceptableException {
		boolean compactive = mediaTypeAccepts.stream().anyMatch(mediaTypeAccept -> mediaTypeAccept.isCompatibleWith(mediaTypePhoto));
	
		if(!compactive) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypeAccepts);
		}
	}

}
