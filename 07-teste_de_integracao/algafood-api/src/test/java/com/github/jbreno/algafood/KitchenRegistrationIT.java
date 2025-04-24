package com.github.jbreno.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.KitchenNotFoundException;
import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.service.KitchenRegistrationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KitchenRegistrationIT {
	
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

	@Test(expected = EntityInUseException.class)
	public void shouldFail_WhenDeleteKitchenInUse() {	
		kitchenService.remove(1L);
	}
	
	@Test(expected = KitchenNotFoundException.class)
	public void shoudFail_WhenKitchenDoesNotExist() {
		kitchenService.remove(100L);
	}
}
