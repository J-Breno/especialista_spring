package com.github.jbreno.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.OrderNotFoundException;
import com.github.jbreno.algafood.domain.model.Order;
import com.github.jbreno.algafood.domain.repository.OrderRepository;

@Service
public class OrderRegistrationService {
	
	private static final String MSG_ORDER_IN_USE 
		= "Pedido de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> list() {
		return orderRepository.findAll();
	}

	public Order search(Long id) {
		return searchOrFail(id);
	}
	
	@Transactional
	public Order save(Order order) {
		return orderRepository.save(order);
	}
	
	public void remove (Long id) {
		try {
			orderRepository.deleteById(id);
			orderRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new OrderNotFoundException("");
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_ORDER_IN_USE, id));
		}
	}	
	
	public Order searchOrFail(Long id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException(""));
	}
}
