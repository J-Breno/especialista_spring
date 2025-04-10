package com.github.jbreno.algafood.di.notification;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Qualifier;

@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface TypeNotifier {

	LevelUrgency value();
	
}

