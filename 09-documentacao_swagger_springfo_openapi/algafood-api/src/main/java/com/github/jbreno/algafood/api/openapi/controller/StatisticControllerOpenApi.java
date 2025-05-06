package com.github.jbreno.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.github.jbreno.algafood.domain.filter.DailySalesFilter;
import com.github.jbreno.algafood.domain.model.dto.DailySale;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Estatísticas")
public interface StatisticControllerOpenApi {
	@ApiOperation("Consulta estatísticas de vendas diárias")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "restaurantId", value = "ID do restaurante", example = "1", dataType = "int"),
		@ApiImplicitParam(name = "dateCreatedStart", value = "Data/hora inicial da criação do  pedido",
		example = "2019-12-01T00:00:00Z", dataType = "date-time"),
		@ApiImplicitParam(name = "dateCreatedEnd", value = "Data/hora final da criação do  pedido",
		example = "2019-12-02T23:59:59Z", dataType = "date-time")
	})
	List<DailySale> checkDailySales(
			DailySalesFilter filter,
			@ApiParam(value = "Deslocamento de horário a ser considerado na consulta em relação", defaultValue = "+00:00") 
			String timeOffSet
			);
	
	ResponseEntity<byte[]> checkDailySalesPdf(
			DailySalesFilter filter,
			String timeOffSet);
}
