package com.github.jbreno.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.github.jbreno.algafood.AlgafoodApiApplication;
import com.github.jbreno.algafood.domain.model.Kitchen;

public class KitchenInclusionMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRegistration kitchenRegistration = applicationContext.getBean(KitchenRegistration.class);
		
		 Kitchen kitchen1 = new Kitchen();
		 kitchen1.setName("Brasileira");
		 
		 Kitchen kitchen2 = new Kitchen();
		 kitchen2.setName("Japonesa");
		 
		kitchen1 = kitchenRegistration.save(kitchen1);
		kitchen2 =  kitchenRegistration.save(kitchen2);
		
		System.out.printf("%d - %s\n", kitchen1.getId(), kitchen1.getName());
		System.out.printf("%d - %s\n", kitchen2.getId(), kitchen2.getName());
	}
}
