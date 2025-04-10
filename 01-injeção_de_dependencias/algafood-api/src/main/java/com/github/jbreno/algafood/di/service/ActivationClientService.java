package com.github.jbreno.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.jbreno.algafood.di.modelo.Client;
import com.github.jbreno.algafood.di.notification.Notifier;


public class ActivationClientService {
	
	@Autowired(required = false)
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
		if(notifier != null) {
		notifier.notify(client, "Seu cadastro no sistema está ativo!");
		} else {
			System.out.println("Não existe notificador, mas cliente foi ativado");
		}
	}
}