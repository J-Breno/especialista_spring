package com.github.jbreno.algafood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.jbreno.algafood.di.notification.Notifier;
import com.github.jbreno.algafood.di.service.ActivationClientService;

@Configuration
public class ServiceConfig {
	@Bean
	public ActivationClientService activationClientService(Notifier notifier) {
		return new ActivationClientService(notifier);
	}
}
