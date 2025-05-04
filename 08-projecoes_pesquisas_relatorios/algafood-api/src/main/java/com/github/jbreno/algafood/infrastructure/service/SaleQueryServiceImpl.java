package com.github.jbreno.algafood.infrastructure.service;

import java.util.ArrayList;
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
	public List<DailySale> checkDailySales(DailySalesFilter filter, String timeOffSet) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(DailySale.class);
		var root = query.from(Order.class);
		var predicates = new ArrayList<>();
		
		var functionConvertTzDateCreated = builder.function(
				"convert_tz",
				Date.class,
				root.get("dateCreated"),
				builder.literal("+00:00"),
				builder.literal(timeOffSet));
		
		var functionDateDateCreated = builder.function("date", Date.class, functionConvertTzDateCreated); 
		
		var selection = builder.construct(DailySale.class, 
				functionDateDateCreated,
				builder.count(root.get("id")),
				builder.sum(root.get("totalValue")));
		
		if(filter.getRestaurantId() != null) {
			predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
		}
		
		if(filter.getCreationDateStart() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dateCreated"), filter.getCreationDateStart()));
		}
		
		if(filter.getCreationDateEnd() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dateCreated"), filter.getCreationDateEnd()));
		}
		
		query.select(selection);
		query.groupBy(functionDateDateCreated);
		
		return manager.createQuery(query).getResultList();
	}

}
