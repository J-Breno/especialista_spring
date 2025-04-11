package com.github.jbreno.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.github.jbreno.algafood.AlgafoodApiApplication;
import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.repository.RestaurantRepository;

public class RestaurantConsultationMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestaurantRepository restaurants = applicationContext.getBean(RestaurantRepository.class);
		
		 List<Restaurant> allRestaurant = restaurants.all();
		 
		 for(Restaurant restaurant : allRestaurant) {  
			 System.out.println(restaurant.getName());
		 }
	}
}
