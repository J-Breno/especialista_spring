package com.github.jbreno.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.jbreno.algafood.di.modelo.Client;
import com.github.jbreno.algafood.di.notification.Notifier;


public class ActivationClientService {
	
	@Qualifier("email")
	@Autowired
	private Notifier notifier;
	
//	@Autowired
//	public ActivationClientService(Notifier notifier) {
//		this.notifier = notifier;
//	}
	
//	@Autowired
//	public void setNotifier(Notifier notifier) {
//		this.notifier = notifier;
//	}

	public void active(Client client) {
		client.active();
		
		notifier.notify(client, "Seu cadastro no sistema está ativo!");
		
	}
}