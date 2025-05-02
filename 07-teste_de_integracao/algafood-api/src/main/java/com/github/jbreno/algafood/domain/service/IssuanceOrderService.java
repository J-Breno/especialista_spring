package com.github.jbreno.algafood.domain.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.model.City;
import com.github.jbreno.algafood.domain.model.Order;
import com.github.jbreno.algafood.domain.model.PaymentMethod;
import com.github.jbreno.algafood.domain.model.Product;
import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.model.User;
import com.github.jbreno.algafood.domain.repository.OrderRepository;
import com.github.jbreno.algafood.domain.repository.ProductRepository;

@Service
public class IssuanceOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentMethodRegistrationService paymentMethodService;

	@Autowired
	private CityRegistrationService cityService;

	@Autowired
	private UserRegistrationService userService;

	@Autowired
	private RestaurantRegistrationService restaurantService;

	@Autowired
	private ProductRepository productRepository;

	@Transactional
	public Order issue(Order order) {
		validateOrder(order);
		validateItens(order);

		if (order.getRestaurant() == null || order.getRestaurant().getShippingFee() == null) {
			throw new BusinessException("Taxa de entrega não definida para o restaurante");
		}

		order.setShippingFee(order.getRestaurant().getShippingFee());
		order.setSubtotal(BigDecimal.ZERO);

		order.calculateTotalValue();

		if (order.getShippingFee() == null || order.getSubtotal() == null || order.getTotalValue() == null) {
			throw new BusinessException("Valores do pedido não foram calculados corretamente");
		}

		return orderRepository.save(order);
	}

	private void validateOrder(Order order) {
		City city = cityService.searchOrFail(order.getDeliveryAddress().getCity().getId());
		User user = userService.searchOrFail(order.getClient().getId());
		Restaurant restaurant = restaurantService.searchOrFail(order.getRestaurant().getId());
		PaymentMethod paymentMethod = paymentMethodService.searchOrFail(order.getPaymentMethod().getId());
	}

	private void validateItens(Order order) {
		order.getItens().forEach(item -> {
			Product product = productRepository
					.findByIdAndRestaurantId(item.getProduct().getId(), order.getRestaurant().getId())
					.orElseThrow(() -> new BusinessException("Produto não encontrado no restaurante"));

			if (product.getPrice() == null) {
				throw new BusinessException("Produto não tem preço definido");
			}

			item.setOrder(order);
			item.setProduct(product);
			item.setUnitPrice(product.getPrice());
		});
	}
}
