package com.github.jbreno.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.jbreno.algafood.di.modelo.Client;
import com.github.jbreno.algafood.di.service.ActivationClientService;

@Controller
public class MyFirstController {
	
	private ActivationClientService activationClientService;
	
	public MyFirstController(ActivationClientService activationClientService) {
		this.activationClientService = activationClientService;
		
		System.out.println("MeuPrimeiroController: " + activationClientService);
	}
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		Client joao = new Client("João", "joao@asd.com", "1234456778");
		
		activationClientService.active(joao);
		
		return "Hello!";
	}
	
	
}
