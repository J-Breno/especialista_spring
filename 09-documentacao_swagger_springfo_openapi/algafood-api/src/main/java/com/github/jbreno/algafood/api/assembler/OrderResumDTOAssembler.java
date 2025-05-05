package com.github.jbreno.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.OrderResumDTO;
import com.github.jbreno.algafood.domain.model.Order;
@Component
public class OrderResumDTOAssembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public OrderResumDTO toModel(Order order) {
		return modelMapper.map(order, OrderResumDTO.class);
	}
	
	public List<OrderResumDTO> toCollectionDTO(List<Order> orders) {
		return orders.stream()
				.map(order -> toModel(order))
				.collect(Collectors.toList());
	}
}
