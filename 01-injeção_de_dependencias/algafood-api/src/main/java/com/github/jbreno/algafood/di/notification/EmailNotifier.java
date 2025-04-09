package com.github.jbreno.algafood.di.notification;

import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.di.modelo.Client;


public class EmailNotifier implements Notifier {
	
	private boolean upperCase = false;
	private String hostServerSmtp;
	
	public EmailNotifier(String hostServerSmpt) {
		this.hostServerSmtp = hostServerSmpt;
		System.out.println("NotificadorEmail");
	}
	
	@Override
	public void notify(Client client, String message) {
		if(this.upperCase) {
			message = message.toUpperCase();
		}
		
		System.out.printf("Notificando %s através do e-mail %s usando SMTP %s: %s/n",
			client.getName(), client.getEmail(), this.hostServerSmtp, message);
	}

	public void setUpperCase(boolean upperCase) {
		this.upperCase = upperCase;
	}
	
	
}
