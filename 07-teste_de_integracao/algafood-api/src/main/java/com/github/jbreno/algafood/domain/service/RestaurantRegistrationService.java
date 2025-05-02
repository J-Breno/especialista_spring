package com.github.jbreno.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.RestaurantNotFoundException;
import com.github.jbreno.algafood.domain.model.City;
import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.model.PaymentMethod;
import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.model.User;
import com.github.jbreno.algafood.domain.repository.RestaurantRepository;

@Service
public class RestaurantRegistrationService {
	
	private static final String MSG_RESTAURANT_IN_USE 
		= "Restaurante de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private KitchenRegistrationService kitchenService;
	
	@Autowired
	private CityRegistrationService cityService;
	
	@Autowired
	private PaymentMethodRegistrationService paymentMethodRegistrationService;
	
	@Autowired
	private UserRegistrationService userService;
	
	public List<Restaurant> list() {
		return restaurantRepository.findAll();
	}

	public Restaurant search(Long id) {
		return searchOrFail(id);
	}
	
	@Transactional
	public Restaurant save(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();
		Long cityId = restaurant.getAddress().getCity().getId();
		
		Kitchen kitchen = kitchenService.searchOrFail(kitchenId);
		City city = cityService.searchOrFail(cityId);
		
		restaurant.setKitchen(kitchen);
		restaurant.getAddress().setCity(city);
		
		return restaurantRepository.save(restaurant);
	}
	
	public void remove (Long id) {
		try {
			restaurantRepository.deleteById(id);
			restaurantRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new RestaurantNotFoundException(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_RESTAURANT_IN_USE, id));
		}
	}	
	
	@Transactional
	public void active(Long id) {
		Restaurant currentRestaurant = searchOrFail(id);
		currentRestaurant.active();
	}
	
	@Transactional
	public void inactivate(Long id) {
		Restaurant currentRestaurant = searchOrFail(id);
		currentRestaurant.inactivate();
	}
	
	@Transactional
	public void open(Long id) {
		Restaurant currentRestaurant = searchOrFail(id);
		currentRestaurant.open();
	}
	
	@Transactional
	public void close(Long id) {
		Restaurant currentRestaurant = searchOrFail(id);
		currentRestaurant.close();
	}
	
	@Transactional
	public void desassociatePaymentMethod(Long restaurantId, Long paymentMethodId) {
		Restaurant restaurant = searchOrFail(restaurantId);
		PaymentMethod paymentMethod	 = paymentMethodRegistrationService.searchOrFail(paymentMethodId);
		
		restaurant.removePaymentMethod(paymentMethod);
	}
	
	@Transactional
	public void associatePaymentMethod(Long restaurantId, Long paymentMethodId) {
		Restaurant restaurant = searchOrFail(restaurantId);
		PaymentMethod paymentMethod	 = paymentMethodRegistrationService.searchOrFail(paymentMethodId);
		
		restaurant.addPaymentMethod(paymentMethod);
	}
	
	@Transactional
	public void desassociateResponsible(Long restaurantId, Long userId) {
		Restaurant restaurant = searchOrFail(restaurantId);
		User user	= userService.searchOrFail(userId);
		
		restaurant.removeResponsible(user);
	}
	
	@Transactional
	public void associateResponsible(Long restaurantId, Long userId) {
		Restaurant restaurant = searchOrFail(restaurantId);
		User user	= userService.searchOrFail(userId);
		
		restaurant.addResponsible(user);
	}
	
	public Restaurant searchOrFail(Long id) {
		return restaurantRepository.findById(id)
				.orElseThrow(() -> new RestaurantNotFoundException(id));
	}
}
