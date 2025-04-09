package com.github.jbreno.algafood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.jbreno.algafood.di.notification.EmailNotifier;

@Configuration
public class NotificationConfig {
	@Bean
	public EmailNotifier emailNotifier() {
		EmailNotifier notifier = new EmailNotifier("smtp.algamail.com.br");
		notifier.setUpperCase(true);
		
		return notifier;
	}
}
