package com.github.jbreno.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.OrderDTO;
import com.github.jbreno.algafood.api.model.PaymentMethodDTO;
import com.github.jbreno.algafood.domain.model.Order;
@Component
public class OrderDTOAssembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public OrderDTO toModel(Order order) {
		 OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);

	        PaymentMethodDTO paymentMethodDTO = modelMapper.map(order.getPaymentMethod(), PaymentMethodDTO.class);
	        orderDTO.setPaymentMethod(paymentMethodDTO);

	        return orderDTO;
	}
	
	public List<OrderDTO> toCollectionDTO(List<Order> orders) {
		return orders.stream()
				.map(order -> toModel(order))
				.collect(Collectors.toList());
	}
}
