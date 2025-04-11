package com.github.jbreno.algafood.di.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.di.modelo.Client;

// @Profile("dev")
@TypeNotifier(LevelUrgency.URGENT)
@Component
public class EmailNotifierMock implements Notifier {
	
	@Value("${notifier.email.host-server}")
	private String host;
	
	@Value("{notifier.email.port-server}")
	private Integer port;
	
	@Override
	public void notify(Client client, String message) {
		System.out.println("Host: " + host);
		System.out.println("Port: " + port);
		
		System.out.printf("MOCK: Notificação seria enviada para %s através do e-mail %s: %s/n",
			client.getName(), client.getEmail(), message);
	}
	
}
