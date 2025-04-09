package com.github.jbreno.algafood.di.notification;

import com.github.jbreno.algafood.di.modelo.Client;

public interface Notifier {

	void notify(Client client, String message);

}