package com.github.jbreno.algafood.di.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.di.modelo.Client;

// @Profile("dev")
@TypeNotifier(LevelUrgency.URGENT)
@Component
public class EmailNotifierMock implements Notifier {
	
	@Autowired
	private NotifierProperties properties;
	
	
	@Override
	public void notify(Client client, String message) {
		System.out.println("Host: " + properties.getHostServer());
		System.out.println("Port: " + properties.getPortServer());
		
		System.out.printf("MOCK: Notificação seria enviada para %s através do e-mail %s: %s/n",
			client.getName(), client.getEmail(), message);
	}
	
}
