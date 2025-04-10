package com.github.jbreno.algafood.di.service;

import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.di.modelo.Client;
import com.github.jbreno.algafood.di.notification.Notifier;


public class ActivationClientService {
	private Notifier notifier;
	
	public ActivationClientService(Notifier notifier) {
		this.notifier = notifier;
	}

	public void active(Client client) {
		client.active();
		notifier.notify(client, "Seu cadastro no sistema está ativo!");
	}
}