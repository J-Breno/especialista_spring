package com.github.jbreno.algafood.infrastructure.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.github.jbreno.algafood.domain.filter.DailySalesFilter;
import com.github.jbreno.algafood.domain.model.Order;
import com.github.jbreno.algafood.domain.model.dto.DailySale;
import com.github.jbreno.algafood.domain.service.SalesQueryService;

@Repository
public class SaleQueryServiceImpl implements SalesQueryService {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<DailySale> checkDailySales(DailySalesFilter filter) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(DailySale.class);
		var root = query.from(Order.class);
		
		var functionDateDateCreated = builder.function("date", Date.class, root.get("dateCreated")); 
		
		var selection = builder.construct(DailySale.class, 
				functionDateDateCreated,
				builder.count(root.get("id")),
				builder.sum(root.get("totalValue")));
		
		query.select(selection);
		query.groupBy(functionDateDateCreated);
		
		return manager.createQuery(query).getResultList();
	}

}
