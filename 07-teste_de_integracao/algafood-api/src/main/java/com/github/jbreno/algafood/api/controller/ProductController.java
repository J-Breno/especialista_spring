package com.github.jbreno.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbreno.algafood.api.assembler.ProductDTOAssembler;
import com.github.jbreno.algafood.api.assembler.ProductInputDisasembler;
import com.github.jbreno.algafood.api.model.ProductDTO;
import com.github.jbreno.algafood.api.model.input.ProductInputDTO;
import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.exception.ProductNotFoundException;
import com.github.jbreno.algafood.domain.model.Product;
import com.github.jbreno.algafood.domain.service.ProductRegistrationService;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/products")
public class ProductController {
	
	@Autowired
	private ProductRegistrationService productService;
	
	@Autowired
	private ProductDTOAssembler productDTOAssembler;
	
	@Autowired
	private ProductInputDisasembler productInputDisassembler;
	
	
	@GetMapping
	public List<ProductDTO> list(@PathVariable Long restaurantId) {
		return productDTOAssembler.toCollectionDTO(productService.list(restaurantId));
	}
	
	@GetMapping("/{id}")
	public ProductDTO search(@PathVariable Long restaurantId, @PathVariable Long id) {
		Product product = productService.searchOrFail(restaurantId, id);
		
		return productDTOAssembler.toModel(product);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDTO addProductToRestaurant(
	    @PathVariable Long restaurantId,
	    @RequestBody @Valid ProductInputDTO productInput
	) {
	    Product product = productInputDisassembler.toDomainObject(productInput);

	    Product savedProduct = productService.save(restaurantId, product);

 	    return productDTOAssembler.toModel(savedProduct);
	}
	
	@PutMapping("/{id}")
	public ProductDTO update(@PathVariable Long restaurantId, @PathVariable Long id,@RequestBody @Valid ProductInputDTO productInputDTO) {
		try {
			Product currentProduct = productService.searchOrFail(restaurantId, id);
			
			productInputDisassembler.copyToDomainObject(productInputDTO, currentProduct);
			
			return productDTOAssembler.toModel(productService.update(restaurantId, id, currentProduct));
		}
		catch(ProductNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long restaurantId, @PathVariable Long id) {	
		productService.remove(restaurantId, id);
	}
}
