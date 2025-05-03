package com.github.jbreno.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.model.Order;

@Service
public class OrderFlowService {
	@Autowired
	private IssuanceOrderService issuanceOrder;

	@Transactional
	public void confirm(Long id) {
		Order order = issuanceOrder.searchOrFail(id);
		order.confirm();
	}
	
	@Transactional
	public void canceled(Long id) {
		Order order = issuanceOrder.searchOrFail(id);
		order.canceled();
	}
	
	@Transactional
	public void delivered(Long id) {
		Order order = issuanceOrder.searchOrFail(id);
		order.delivered();
	}
	
}
