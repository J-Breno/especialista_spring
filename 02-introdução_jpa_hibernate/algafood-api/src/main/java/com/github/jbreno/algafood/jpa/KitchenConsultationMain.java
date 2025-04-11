package com.github.jbreno.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.github.jbreno.algafood.AlgafoodApiApplication;
import com.github.jbreno.algafood.domain.model.Kitchen;

public class KitchenConsultationMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRegistration kitchenRegistration = applicationContext.getBean(KitchenRegistration.class);
		
		 List<Kitchen> kitchens = kitchenRegistration.listar();
		 
		 for(Kitchen kitchen : kitchens) {  
			 System.out.println(kitchen.getName());
		 }
	}
}
