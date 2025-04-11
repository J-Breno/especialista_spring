package com.github.jbreno.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.di.modelo.Client;
import com.github.jbreno.algafood.di.notification.LevelUrgency;
import com.github.jbreno.algafood.di.notification.Notifier;
import com.github.jbreno.algafood.di.notification.TypeNotifier;

@Component
public class ActivationClientService {
	
	@TypeNotifier(LevelUrgency.URGENT)
	@Autowired
	private Notifier notifier;
	

	
	public void active(Client client) {
		client.active();
		
		notifier.notify(client, "Seu cadastro no sistema está ativo!");
		
	}
}