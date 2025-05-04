package com.github.jbreno.algafood.infrastructure.service.report;

import org.springframework.stereotype.Service;

import com.github.jbreno.algafood.domain.filter.DailySalesFilter;
import com.github.jbreno.algafood.domain.service.SaleReportService;

@Service
public class PdfSaleReportService implements SaleReportService{

	@Override
	public byte[] issueDailySales(DailySalesFilter filter, String timeOffSet) {
		return null;
	}

}
