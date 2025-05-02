package com.github.jbreno.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.input.OrderInputDTO;
import com.github.jbreno.algafood.domain.model.Order;
import com.github.jbreno.algafood.domain.model.PaymentMethod;
import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.model.User;

@Component
public class OrderInputDisasembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Order toDomainObject(OrderInputDTO orderInput) {
		return modelMapper.map(orderInput, Order.class);
	}
	
	public void copyToDomainObject(OrderInputDTO orderInputDTO, Order order) {
		order.setPaymentMethod(new PaymentMethod());
		order.setRestaurant(new Restaurant());
		order.setClient(new User());
		modelMapper.map(orderInputDTO, order);
	}
	
}
