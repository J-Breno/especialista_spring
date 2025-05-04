package com.github.jbreno.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbreno.algafood.domain.filter.DailySalesFilter;
import com.github.jbreno.algafood.domain.model.dto.DailySale;
import com.github.jbreno.algafood.domain.service.SalesQueryService;

@RestController
@RequestMapping(path = "/statistics")
public class StatisticsConstroller {
	
	@Autowired
	private SalesQueryService saleQueryService;
	
	@GetMapping("/daily-sales")
	public List<DailySale> checkDailySales(DailySalesFilter filter,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {
		return saleQueryService.checkDailySales(filter, timeOffSet);
	}
}
