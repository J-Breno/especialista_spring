package com.github.jbreno.algafood.di.notification;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.di.modelo.Client;

@Primary
@Component
public class EmailNotifier implements Notifier {
	
	@Override
	public void notify(Client client, String message) {
		System.out.printf("Notificando %s através do e-mail %s: %s/n",
			client.getName(), client.getEmail(), message);
	}
	
}
