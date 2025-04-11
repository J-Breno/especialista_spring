package com.github.jbreno.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.github.jbreno.algafood.AlgafoodApiApplication;
import com.github.jbreno.algafood.domain.model.Kitchen;

public class KitchenExclusionMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRegistration kitchenRegistration = applicationContext.getBean(KitchenRegistration.class);
		
		 Kitchen kitchen1 = new Kitchen();
		 kitchen1.setId(1L);
		 
		 kitchenRegistration.remove(kitchen1);
	}
}
