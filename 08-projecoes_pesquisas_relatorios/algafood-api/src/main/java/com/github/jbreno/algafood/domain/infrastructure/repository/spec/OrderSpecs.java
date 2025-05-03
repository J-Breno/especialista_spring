package com.github.jbreno.algafood.domain.infrastructure.repository.spec;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;

import com.github.jbreno.algafood.domain.model.Order;
import com.github.jbreno.algafood.domain.repository.filter.OrderFilter;
import javax.persistence.criteria.Predicate;

public class OrderSpecs {
	public static Specification<Order> usingFilter(OrderFilter filter) {
		return (root, query, builder) -> {
			root.fetch("restaurant").fetch("kitchen");
			root.fetch("client");
			var predicates = new ArrayList<Predicate>();
			
			if(filter.getClientId() != null) {
				predicates.add(builder.equal(root.get("client"), filter.getClientId())); 
			}
			
			if(filter.getRestaurantId() != null) {
				predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId())); 
			}

			if(filter.getCreationDateStart() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dateCreated"), filter.getCreationDateStart())); 
			}
			
			if(filter.getCreationDateEnd() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dateCreated"), filter.getCreationDateEnd())); 
			}
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}  
