package com.github.jbreno.algafood.di.notification;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.di.modelo.Client;

@Profile("prod")
@TypeNotifier(LevelUrgency.URGENT)
@Component
public class EmailNotifier implements Notifier {
	
	public EmailNotifier() {
		System.out.println("Notificador Email REAL");
	}
	
	@Override
	public void notify(Client client, String message) {
		System.out.printf("Notificando %s através do e-mail %s: %s/n",
			client.getName(), client.getEmail(), message);
	}
	
}
