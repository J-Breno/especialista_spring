package com.github.jbreno.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbreno.algafood.api.openapi.controller.StatisticControllerOpenApi;
import com.github.jbreno.algafood.domain.filter.DailySalesFilter;
import com.github.jbreno.algafood.domain.model.dto.DailySale;
import com.github.jbreno.algafood.domain.service.SaleReportService;
import com.github.jbreno.algafood.domain.service.SalesQueryService;

@RestController
@RequestMapping(path = "/statistics")
public class StatisticsConstroller implements StatisticControllerOpenApi{
	
	@Autowired
	private SalesQueryService saleQueryService;
	
	@Autowired
	private SaleReportService saleReportService;
	
	@GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DailySale> checkDailySales(DailySalesFilter filter,
			 @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {
		return saleQueryService.checkDailySales(filter, timeOffSet);
	}
	
	@GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> checkDailySalesPdf(DailySalesFilter filter,
			 @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {
		byte[] bytesPdf = saleReportService.issueDailySales(filter, timeOffSet);
		
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=daily-sales.pdf");
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).headers(headers).body(bytesPdf);
	}
}
