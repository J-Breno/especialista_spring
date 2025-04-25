package com.github.jbreno.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.jbreno.algafood.api.model.mixin.RestaurantMixin;
import com.github.jbreno.algafood.domain.model.Restaurant;

@Component
public class JacksonMixinModule extends SimpleModule{
	private static final long serialVersionUID = 1L;

	public JacksonMixinModule() {
		setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
	}
}
