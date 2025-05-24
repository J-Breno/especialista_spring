package com.github.jbreno.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.jbreno.algafood.api.assembler.PhotoProductDTOAssembler;
import com.github.jbreno.algafood.api.model.PhotoProductDTO;
import com.github.jbreno.algafood.api.model.input.PhotoProductInput;
import com.github.jbreno.algafood.domain.model.PhotoProduct;
import com.github.jbreno.algafood.domain.model.Product;
import com.github.jbreno.algafood.domain.service.CatalogPhotoProductService;
import com.github.jbreno.algafood.domain.service.ProductRegistrationService;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {
	
	@Autowired
	private ProductRegistrationService productService;
	
	@Autowired
	private CatalogPhotoProductService service;
	
	@Autowired
	private PhotoProductDTOAssembler photoProductDTOAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PhotoProductDTO updatePhoto(
            @PathVariable Long restaurantId,
            @PathVariable Long productId,
            @Valid PhotoProductInput photoProductInput) {
    	Product product = productService.searchOrFail(restaurantId, productId);
    	PhotoProduct photo = new PhotoProduct();
    	MultipartFile file = photoProductInput.getFile();
    	
    	photo.setProduct(product);
    	photo.setDescription(photoProductInput.getDescription());
    	photo.setContentType(file.getContentType());
    	photo.setSize(file.getSize());
    	photo.setFileName(file.getOriginalFilename());
    	
       PhotoProduct photoSave = service.save(photo);
       
       return photoProductDTOAssembler.toModel(photoSave);
    }

}
