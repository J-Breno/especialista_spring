package com.github.jbreno.algafood.domain.service;

import java.util.List;

import com.github.jbreno.algafood.domain.filter.DailySalesFilter;
import com.github.jbreno.algafood.domain.model.dto.DailySale;

public interface SalesQueryService {

	List<DailySale> checkDailySales(DailySalesFilter filter, String timeOffSet);
	
}
