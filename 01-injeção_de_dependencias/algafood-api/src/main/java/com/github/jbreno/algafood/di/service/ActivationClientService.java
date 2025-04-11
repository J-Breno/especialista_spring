package com.github.jbreno.algafood.di.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.jbreno.algafood.di.modelo.Client;
import com.github.jbreno.algafood.di.notification.LevelUrgency;
import com.github.jbreno.algafood.di.notification.Notifier;
import com.github.jbreno.algafood.di.notification.TypeNotifier;


public class ActivationClientService {
	
	@TypeNotifier(LevelUrgency.URGENT)
	@Autowired
	private Notifier notifier;
	
	@PostConstruct
	public void init() {
		System.out.println("INIT " + notifier);
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("DESTROY");
	}
	
	public void active(Client client) {
		client.active();
		
		notifier.notify(client, "Seu cadastro no sistema está ativo!");
		
	}
}