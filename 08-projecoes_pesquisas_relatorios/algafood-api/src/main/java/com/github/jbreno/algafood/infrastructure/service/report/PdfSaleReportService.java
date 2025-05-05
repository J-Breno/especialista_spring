package com.github.jbreno.algafood.infrastructure.service.report;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jbreno.algafood.domain.filter.DailySalesFilter;
import com.github.jbreno.algafood.domain.service.SaleReportService;
import com.github.jbreno.algafood.domain.service.SalesQueryService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfSaleReportService implements SaleReportService {

	@Autowired
	private SalesQueryService salesQueryService;

	@Override
	public byte[] issueDailySales(DailySalesFilter filter, String timeOffSet) {
		try {
			var inputStream = this.getClass().getResourceAsStream("/reports/daily_sales.jasper");
			var param = new HashMap<String, Object>();
			param.put("REPORT_LOCALE", new Locale("pt", "BR"));
			var dailySale = salesQueryService.checkDailySales(filter, timeOffSet);
			var dataSource = new JRBeanCollectionDataSource(dailySale);

			var jasperPrint = JasperFillManager.fillReport(inputStream, param, dataSource);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			throw new ReportException("Não foi possível emitir relátorio de vendas diárias", e);
		}
	}
}