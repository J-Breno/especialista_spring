package com.github.jbreno.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.input.ProductInputDTO;
import com.github.jbreno.algafood.domain.model.Product;

@Component
public class ProductInputDisasembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Product toDomainObject(ProductInputDTO productInput) {
		return modelMapper.map(productInput, Product.class);
	}
	
	public void copyToDomainObject(ProductInputDTO productInputDTO, Product product) {
		modelMapper.map(productInputDTO, product);
	}
	
}
