package com.github.jbreno.algafood.domain.service;

import com.github.jbreno.algafood.domain.filter.DailySalesFilter;

public interface SaleReportService {
	
	byte[] issueDailySales(DailySalesFilter filter, String timeOffSet);
	
}
