package com.github.jbreno.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.github.jbreno.algafood.AlgafoodApiApplication;
import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.repository.KitchenRepository;

public class KitchenInclusionMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);
		
		 Kitchen kitchen1 = new Kitchen();
		 kitchen1.setName("Brasileira");
		 
		 Kitchen kitchen2 = new Kitchen();
		 kitchen2.setName("Japonesa");
		 
		kitchen1 = kitchenRepository.save(kitchen1);
		kitchen2 =  kitchenRepository.save(kitchen2);
		
		System.out.printf("%d - %s\n", kitchen1.getId(), kitchen1.getName());
		System.out.printf("%d - %s\n", kitchen2.getId(), kitchen2.getName());
	}
}
