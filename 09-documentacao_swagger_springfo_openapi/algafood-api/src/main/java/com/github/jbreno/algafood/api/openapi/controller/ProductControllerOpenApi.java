package com.github.jbreno.algafood.api.openapi.controller;

import java.util.List;

import com.github.jbreno.algafood.api.exceptionhandler.Problem;
import com.github.jbreno.algafood.api.model.ProductDTO;
import com.github.jbreno.algafood.api.model.input.ProductInputDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface ProductControllerOpenApi {
	@ApiOperation("Lista produtos de um restaurante")
	public List<ProductDTO> list(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restaurantId,
			@ApiParam(value = "incluir inativo", example = "false", required = false) boolean includeInactive);

	@ApiOperation("Busca uma produto por ID")
	@ApiResponses({ @ApiResponse(code = 400, message = "ID do produto inválido", response = Problem.class),
			@ApiResponse(code = 404, message = "Produto não encontrado", response = Problem.class) })
	public ProductDTO search(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restaurantId,
			@ApiParam(value = "ID de uma cidade", example = "1") Long id);

	@ApiOperation("Cadastra um produto")
	@ApiResponses({ @ApiResponse(code = 201, message = "Produto cadastrado") })
	public ProductDTO addProductToRestaurant(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restaurantId,
			@ApiParam(name = "corpo", value = "Representação de uma novo produto", required = true) ProductInputDTO productInputDTO);

	@ApiOperation("Atualiza um produto por ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Produto atualizado"),
			@ApiResponse(code = 404, message = "Produto não encontrado", response = Problem.class) })
	public ProductDTO update(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restaurantId,
			@ApiParam(value = "ID de uma cidade", example = "1") Long id,
			@ApiParam(name = "corpo", value = "Representação de um produto com os novos dados") ProductInputDTO productInputDTO);

	@ApiOperation("Deleta uma produto por ID")
	@ApiResponses({ @ApiResponse(code = 204, message = "produto excluído"),
			@ApiResponse(code = 404, message = "Produto não encontrada", response = Problem.class) })
	public void remove(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restaurantId,
			@ApiParam(value = "ID de uma cidade", example = "1") Long id);
}
