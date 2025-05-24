package com.github.jbreno.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.PhotoProductDTO;
import com.github.jbreno.algafood.domain.model.PhotoProduct;
@Component
public class PhotoProductDTOAssembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public PhotoProductDTO toModel(PhotoProduct photoProduct) {
		return modelMapper.map(photoProduct, PhotoProductDTO.class);
	}
}
