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

import com.fasterxml.jackson.annotation.JsonView;
import com.github.jbreno.algafood.api.assembler.RestaurantDTOAssembler;
import com.github.jbreno.algafood.api.assembler.RestaurantInputDisasembler;
import com.github.jbreno.algafood.api.model.RestaurantDTO;
import com.github.jbreno.algafood.api.model.input.RestaurantInputDTO;
import com.github.jbreno.algafood.api.model.view.RestaurantView;
import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.exception.CityNotFoundException;
import com.github.jbreno.algafood.domain.exception.RestaurantNotFoundException;
import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.service.RestaurantRegistrationService;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantRegistrationService restaurantService;
	
	@Autowired
	private RestaurantDTOAssembler restaurantDTOAssembler;
	
	@Autowired
	private RestaurantInputDisasembler restaurantInputDisasembler;

	@JsonView(RestaurantView.Resum.class)
	@GetMapping
	public List<RestaurantDTO> list() {
		return restaurantDTOAssembler.toCollectionDTO(restaurantService.list());
	}
	
	@JsonView(RestaurantView.NameOnly.class)
	@GetMapping(params = "projections=name-only")
	public List<RestaurantDTO> listNameOnly() {
		return list();
	}
	
//	@GetMapping
//	public MappingJacksonValue list(@RequestParam(required = false) String projections) {
//		List<Restaurant> restaurants = restaurantRepository.findAll();
//		List<RestaurantDTO> restaurantsModel = restaurantDTOAssembler.toCollectionDTO(restaurants);
//		
//		MappingJacksonValue restaurantWrapper = new MappingJacksonValue(restaurantsModel);
//		
//		restaurantWrapper.setSerializationView(RestaurantView.Resum.class);
//		
//		if("name-only".equals(projections)) {
//			restaurantWrapper.setSerializationView(RestaurantView.NameOnly.class);
//		} else if("complet".equals(projections)) {
//			restaurantWrapper.setSerializationView(null);
//		}
//		
//		return restaurantWrapper;
//	}
	
//	@GetMapping
//	public List<RestaurantDTO> list() {
//		return restaurantDTOAssembler.toCollectionDTO(restaurantService.list());
//	}
//	
//	@JsonView(RestaurantView.Resum.class)
//	@GetMapping(params = "projection=resum")
//	public List<RestaurantDTO> listResum() {
//		return list();
//	}
//	
//	@JsonView(RestaurantView.NameOnly.class)
//	@GetMapping(params = "projection=name-only")
//	public List<RestaurantDTO> listNameOnly() {
//		return list();
//	}
	
	@GetMapping("/{id}")
	public RestaurantDTO search(@PathVariable Long id) {
		Restaurant restaurant = restaurantService.searchOrFail(id);
		
		return restaurantDTOAssembler.toModel(restaurant);
	}

	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestaurantDTO add(@RequestBody @Valid RestaurantInputDTO restaurantInput) {
		try {
			Restaurant restaurant =  restaurantInputDisasembler.toDomainObject(restaurantInput);
			return restaurantDTOAssembler.toModel(restaurantService.save(restaurant));
		}
		catch(RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	public RestaurantDTO update(@PathVariable Long id,@RequestBody @Valid RestaurantInputDTO restaurantInputDTO) {
		try {
			Restaurant currentRestaurant = restaurantService.searchOrFail(id);
			
			restaurantInputDisasembler.copyToDomainObject(restaurantInputDTO, currentRestaurant);
			
			return restaurantDTOAssembler.toModel(restaurantService.save(currentRestaurant));
		}
		catch(RestaurantNotFoundException | CityNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {	
		restaurantService.remove(id);
	}
	
	@PutMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void active(@PathVariable Long id) {
		restaurantService.active(id);
	}
	
	@DeleteMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inactivate(@PathVariable Long id) {
		restaurantService.inactivate(id);
	}
	
	@PutMapping("/activations")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void activeMultiple(@RequestBody List<Long> ids) {
		try {
			restaurantService.active(ids);
		} catch (RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/desactivations")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desactiveMultiple(@RequestBody List<Long> ids) {
		try {
			restaurantService.inactivate(ids);
		} catch (RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}/opening")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void open(@PathVariable Long id) {
		restaurantService.open(id);
	}
	
	@PutMapping("/{id}/closing")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void closing(@PathVariable Long id) {
		restaurantService.close(id);
	}
}
