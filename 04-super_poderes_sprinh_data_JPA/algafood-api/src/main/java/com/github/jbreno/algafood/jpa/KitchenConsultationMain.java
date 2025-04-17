package com.github.jbreno.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.github.jbreno.algafood.AlgafoodApiApplication;
import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.repository.KitchenRepository;

public class KitchenConsultationMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRepository kitchens = applicationContext.getBean(KitchenRepository.class);
		
		 List<Kitchen> allKitchen = kitchens.all();
		 
		 for(Kitchen kitchen : allKitchen) {  
			 System.out.println(kitchen.getName());
		 }
	}
}
