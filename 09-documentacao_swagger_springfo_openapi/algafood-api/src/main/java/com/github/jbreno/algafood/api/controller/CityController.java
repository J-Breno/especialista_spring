package com.github.jbreno.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.github.jbreno.algafood.api.assembler.CityDTOAssembler;
import com.github.jbreno.algafood.api.assembler.CityInputDisasembler;
import com.github.jbreno.algafood.api.exceptionhandler.Problem;
import com.github.jbreno.algafood.api.model.CityDTO;
import com.github.jbreno.algafood.api.model.input.CityInputDTO;
import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.exception.StateNotFoundException;
import com.github.jbreno.algafood.domain.model.City;
import com.github.jbreno.algafood.domain.service.CityRegistrationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cities")
@RestController
@RequestMapping("/cities")
public class CityController {
	
	@Autowired
	private CityRegistrationService cityService;
	
	@Autowired
	private CityDTOAssembler cityDTOAssembler;
	
	@Autowired
	private CityInputDisasembler cityInputDisasembler;
	
	@ApiOperation("Lista cidades")
	@GetMapping
	public List<CityDTO> list() {
		return cityDTOAssembler.toCollectionDTO(cityService.list());
	}
	
	@ApiOperation("Busca um cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response =  Problem.class)
	})
	@GetMapping("/{id}")
	public CityDTO search(
			@ApiParam(value = "ID de uma cidade", example = "1") 
			@PathVariable Long id) {
		return cityDTOAssembler.toModel(cityService.searchOrFail(id));
	}
	
	@ApiOperation("Cadastra uma cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade cadastrada"),
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CityDTO add(
			@ApiParam(name = "corpo", value = "Representação de uma nova cidade")
			@RequestBody @Valid CityInputDTO cityInputDTO) {
		try {
			City city =  cityInputDisasembler.toDomainObject(cityInputDTO);
			return cityDTOAssembler.toModel(cityService.save(city));
		}
		catch(StateNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cidade atualizada"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response =  Problem.class)
	})
	@PutMapping("/{id}")
	public CityDTO update(
			@ApiParam(value = "ID de uma cidade", example = "1")
			@PathVariable Long id,
			@ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados")
			@RequestBody @Valid CityInputDTO cityInputDTO) {
		try {
			City currentCity = cityService.searchOrFail(id);
			
			cityInputDisasembler.copyToDomainObject(cityInputDTO, currentCity);
			
			return cityDTOAssembler.toModel(cityService.save(currentCity));
		}
		catch(StateNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@ApiOperation("Deleta uma cidade")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cidade excluída"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response =  Problem.class)
	})
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(
			@ApiParam(value = "ID de uma cidade", example = "1")
			@PathVariable Long id) {	
		cityService.remove(id);
	}
}