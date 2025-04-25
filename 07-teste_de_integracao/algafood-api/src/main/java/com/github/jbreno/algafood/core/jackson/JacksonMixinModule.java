package com.github.jbreno.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.jbreno.algafood.api.model.mixin.CityMixin;
import com.github.jbreno.algafood.api.model.mixin.GroupMixin;
import com.github.jbreno.algafood.api.model.mixin.KitchenMixin;
import com.github.jbreno.algafood.api.model.mixin.ProductMixin;
import com.github.jbreno.algafood.api.model.mixin.RestaurantMixin;
import com.github.jbreno.algafood.domain.model.City;
import com.github.jbreno.algafood.domain.model.Group;
import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.model.Product;
import com.github.jbreno.algafood.domain.model.Restaurant;

@Component
public class JacksonMixinModule extends SimpleModule{
	private static final long serialVersionUID = 1L;

	public JacksonMixinModule() {
		setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
		setMixInAnnotation(City.class, CityMixin.class);
		setMixInAnnotation(Group.class, GroupMixin.class);
		setMixInAnnotation(Kitchen.class, KitchenMixin.class);
		setMixInAnnotation(Product.class, ProductMixin.class);
	}
}
