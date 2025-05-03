package com.github.jbreno.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.github.jbreno.algafood.domain.infrastructure.repository.spec.OrderSpecs;
import com.github.jbreno.algafood.domain.model.Order;
import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.model.User;
import com.github.jbreno.algafood.domain.repository.OrderRepository;
import com.github.jbreno.algafood.domain.repository.filter.OrderFilter;
import com.github.jbreno.algafood.domain.service.IssuanceOrderService;
import com.github.jbreno.algafood.domain.service.OrderRegistrationService;
import com.github.jbreno.algafood.domain.service.RestaurantRegistrationService;
import com.github.jbreno.algafood.domain.service.UserRegistrationService;


@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderRegistrationService orderService;

	@Autowired
	private UserRegistrationService userService;

	@Autowired
	private RestaurantRegistrationService restaurantService;

	@Autowired
	private OrderDTOAssembler orderDTOAssembler;

	@Autowired
	private OrderResumDTOAssembler orderResumDTOAssembler;

	@Autowired
	private OrderInputDisasembler orderInputDisasembler;

	@Autowired
	private IssuanceOrderService issuanceOrder;
	
	@Autowired
	private OrderRepository orderRepository;

	@GetMapping
	public Page<OrderResumDTO> search(OrderFilter orderFilter, @PageableDefault(size = 10) Pageable pageable) {
		Page<Order> ordersPage = orderRepository.findAll(OrderSpecs.usingFilter(orderFilter), pageable);
		
		List<OrderResumDTO> ordersResumDto = orderResumDTOAssembler.toCollectionDTO(ordersPage.getContent());
		
		Page<OrderResumDTO> ordersResumDtoPage = new PageImpl<>(ordersResumDto, pageable, ordersPage.getTotalElements());
		
		return ordersResumDtoPage;
	}

//	@GetMapping
//	public MappingJacksonValue list(@RequestParam(required = false) String camps) {
//		List<Order> orders = orderService.list();
//		List<OrderResumDTO> orderDto =  orderResumDTOAssembler.toCollectionDTO(orders);
//		
//		MappingJacksonValue ordersWrapper = new MappingJacksonValue(orderDto);
//		
//		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//		filterProvider.addFilter("orderFilter", SimpleBeanPropertyFilter.serializeAll());
//		
//		if(StringUtils.isNotBlank(camps)) {
//			filterProvider.addFilter("orderFilter", SimpleBeanPropertyFilter.filterOutAllExcept(camps.split(",")));
//		}
//			
//		ordersWrapper.setFilters(filterProvider);
//		
//		return ordersWrapper;
//	}
	
	@GetMapping("/{code}")
	public OrderDTO search(@PathVariable String code) {
		Order order = issuanceOrder.searchOrFail(code);

		return orderDTOAssembler.toModel(order);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderDTO add(@RequestBody @Valid OrderInputDTO orderInput) {
		try {
			Order order = orderInputDisasembler.toDomainObject(orderInput);

			User client = userService.searchOrFail(1L);
			Restaurant restaurant = restaurantService.searchOrFail(order.getRestaurant().getId());

			order.setClient(client);
			order.setRestaurant(restaurant);

			order = issuanceOrder.issue(order);

			return orderDTOAssembler.toModel(order);
		} catch (OrderNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping("/{id}")
	public OrderDTO update(@PathVariable Long id, @RequestBody @Valid OrderInputDTO OrderInputDTO) {
		try {
			Order currentOrder = orderService.searchOrFail(id);

			orderInputDisasembler.copyToDomainObject(OrderInputDTO, currentOrder);

			return orderDTOAssembler.toModel(orderService.save(currentOrder));
		} catch (OrderNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		orderService.remove(id);
	}

}
