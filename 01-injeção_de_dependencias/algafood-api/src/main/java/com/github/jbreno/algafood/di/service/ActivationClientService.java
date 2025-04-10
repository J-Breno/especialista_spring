package com.github.jbreno.algafood.di.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.jbreno.algafood.di.modelo.Client;
import com.github.jbreno.algafood.di.notification.Notifier;


public class ActivationClientService {
	
	@Autowired
	private List<Notifier> notifiers;
	
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
		
		for(Notifier notifier : notifiers) {
			notifier.notify(client, "Seu cadastro no sistema está ativo!");
		}
	}
}