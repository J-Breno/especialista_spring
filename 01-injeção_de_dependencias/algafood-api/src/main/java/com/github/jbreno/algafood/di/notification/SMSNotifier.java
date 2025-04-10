package com.github.jbreno.algafood.di.notification;

import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.di.modelo.Client;

@Component
public class SMSNotifier implements Notifier {
	
	@Override
	public void notify(Client client, String message) {
		System.out.printf("Notificando %s por SMS através do telefone %s: %s/n",
			client.getName(), client.getPhone(), message);
	}
	
}
