package com.github.jbreno.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbreno.algafood.api.ResourceUriHelper;
import com.github.jbreno.algafood.api.assembler.KitchenDTOAssembler;
import com.github.jbreno.algafood.api.assembler.KitchenInputDisasembler;
import com.github.jbreno.algafood.api.model.KitchenDTO;
import com.github.jbreno.algafood.api.model.input.KitchenInputDTO;
import com.github.jbreno.algafood.api.openapi.controller.KitchenControllerOpenApi;
import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.repository.KitchenRepository;
import com.github.jbreno.algafood.domain.service.KitchenRegistrationService;


@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController implements KitchenControllerOpenApi{
	
	
	@Autowired
	private KitchenRegistrationService kitchenService;
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Autowired
	private KitchenInputDisasembler kitchenInputDisasembler;
	
	@Autowired
	private KitchenDTOAssembler kitchenDTOAssembler;
	
	@GetMapping
	public Page<KitchenDTO> list(@PageableDefault(size = 10) Pageable pageable) {
		Page<Kitchen> kitchensPage = kitchenRepository.findAll(pageable);
		List<KitchenDTO> kitchenDto = kitchenDTOAssembler.toCollectionDTO(kitchensPage.getContent());
		
		Page<KitchenDTO> kitchenDtoPage = new PageImpl<>(kitchenDto, pageable, kitchensPage.getTotalElements());
		return kitchenDtoPage;
	}
	
	@GetMapping("/{id}")
	public KitchenDTO search(@PathVariable Long id) {
		Kitchen kitchen = kitchenService.searchOrFail(id);
		return kitchenDTOAssembler.toModel(kitchen);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public KitchenDTO add(@RequestBody @Valid KitchenInputDTO kitchenInput) {
		Kitchen kitchen =  kitchenInputDisasembler.toDomainObject(kitchenInput);
		kitchen = kitchenService.save(kitchen);
		
		KitchenDTO kitchenDTO = kitchenDTOAssembler.toModel(kitchen);
		
		ResourceUriHelper.addUriInResponseHeader(kitchenDTO.getId());
		
		return kitchenDTO;
	}
	
	@PutMapping("/{id}")
	public KitchenDTO update(@PathVariable Long id, @RequestBody @Valid KitchenInputDTO kitchen) {	
		Kitchen currentKitchen = kitchenService.searchOrFail(id);
	
		kitchenInputDisasembler.copyToDomainObject(kitchen, currentKitchen);
		
		return kitchenDTOAssembler.toModel(kitchenService.save(currentKitchen));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {	
		kitchenService.remove(id);
	}
}
