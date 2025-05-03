package com.github.jbreno.algafood.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.model.Order;
import com.github.jbreno.algafood.domain.model.OrderStatus;

@Service
public class OrderFlowService {
	@Autowired
	private IssuanceOrderService issuanceOrder;

	@Transactional
	public void confirm(Long id) {
		Order order = issuanceOrder.searchOrFail(id);
		
		if(!order.getStatus().equals(OrderStatus.CREATING)) {
			throw new BusinessException(
					String.format("Status do pedido %d não pode ser alterado de %s para %s", 
							order.getId(), order.getStatus().getDescription(), OrderStatus.CONFIRMED));
		}
		
		order.setStatus(OrderStatus.CONFIRMED);
		order.setConfirmationDate(OffsetDateTime.now() );
	}
	
}
