package com.github.jbreno.algafood.di.notification;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.di.modelo.Client;

@Profile("dev")
@TypeNotifier(LevelUrgency.URGENT)
@Component
public class EmailNotifierMock implements Notifier {
	
	public EmailNotifierMock() {
		System.out.println("Notificador Email MOCK");
	}
	
	@Override
	public void notify(Client client, String message) {
		System.out.printf("MOCK: Notificação seria enviada para %s através do e-mail %s: %s/n",
			client.getName(), client.getEmail(), message);
	}
	
}
