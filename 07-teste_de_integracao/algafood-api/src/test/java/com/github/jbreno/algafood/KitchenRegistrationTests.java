package com.github.jbreno.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.jbreno.algafood.domain.exception.KitchenNotFoundException;
import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.service.KitchenRegistrationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KitchenRegistrationTests {
	
	@Autowired
	private KitchenRegistrationService kitchenService;

	@Test
	public void shouldKitchenRegistrationSuccessfully_WhenKitchenCorrect() {
		// cenário
		Kitchen newKitchen = new Kitchen();
		newKitchen.setName("Chinesa");
		
		// ação
		newKitchen = kitchenService.save(newKitchen);
		
		// validação
		assertThat(newKitchen).isNotNull();
		assertThat(newKitchen.getId()).isNotNull();
	}

	@Test(expected = KitchenNotFoundException.class)
	public void shouldFailToRegisterKitchen_WhenWithoutName() {
		Kitchen newKitchen = new Kitchen();
		newKitchen.setName(null);
		
		newKitchen = kitchenService.save(newKitchen);
		
		
	}
}
