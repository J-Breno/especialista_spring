package com.github.jbreno.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.input.OrderInputDTO;
import com.github.jbreno.algafood.domain.model.Order;
import com.github.jbreno.algafood.domain.model.OrderItem;
import com.github.jbreno.algafood.domain.model.PaymentMethod;
import com.github.jbreno.algafood.domain.model.Product;
import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.model.User;

@Component
public class OrderInputDisasembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Order toDomainObject(OrderInputDTO orderInput) {
	    Order order = modelMapper.map(orderInput, Order.class);

	    List<OrderItem> items = orderInput.getItens().stream()
	        .map(itemInput -> {
	            OrderItem item = new OrderItem();
	            Product product = new Product();
	            product.setId(itemInput.getProductId());

	            item.setProduct(product);
	            item.setQuantity(itemInput.getQuantity());
	            item.setOrder(order);
	            return item;
	        }).collect(Collectors.toList());

	    order.setItens(items);

	    return order;
	}
	public void copyToDomainObject(OrderInputDTO orderInputDTO, Order order) {
		order.setPaymentMethod(new PaymentMethod());
		order.setRestaurant(new Restaurant());
		order.setClient(new User());
		modelMapper.map(orderInputDTO, order);
	}
	
}
