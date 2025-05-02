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

import com.github.jbreno.algafood.api.assembler.OrderDTOAssembler;
import com.github.jbreno.algafood.api.assembler.OrderInputDisasembler;
import com.github.jbreno.algafood.api.assembler.OrderResumDTOAssembler;
import com.github.jbreno.algafood.api.model.OrderDTO;
import com.github.jbreno.algafood.api.model.OrderResumDTO;
import com.github.jbreno.algafood.api.model.input.OrderInputDTO;
import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.exception.OrderNotFoundException;
import com.github.jbreno.algafood.domain.model.Order;
import com.github.jbreno.algafood.domain.service.OrderRegistrationService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	
	@Autowired
	private OrderRegistrationService orderService;
	
	@Autowired
	private OrderDTOAssembler orderDTOAssembler;
	
	@Autowired
	private OrderResumDTOAssembler orderResumDTOAssembler;
	
	@Autowired
	private OrderInputDisasembler orderInputDisasembler;
	
	
	@GetMapping
	public List<OrderResumDTO> list() {
		return orderResumDTOAssembler.toCollectionDTO(orderService.list());
	}
	
	@GetMapping("/{id}")
	public OrderDTO search(@PathVariable Long id) {
		Order order = orderService.searchOrFail(id);
		
		return orderDTOAssembler.toModel(order);
	}

	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderDTO add(@RequestBody @Valid OrderInputDTO orderInput) {
		try {
			Order order =  orderInputDisasembler.toDomainObject(orderInput);
			return orderDTOAssembler.toModel(orderService.save(order));
		}
		catch(OrderNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	public OrderDTO update(@PathVariable Long id,@RequestBody @Valid OrderInputDTO OrderInputDTO) {
		try {
			Order currentOrder = orderService.searchOrFail(id);
			
			orderInputDisasembler.copyToDomainObject(OrderInputDTO, currentOrder);
			
			return orderDTOAssembler.toModel(orderService.save(currentOrder));
		}
		catch(OrderNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {	
		orderService.remove(id);
	}
	
	
}
