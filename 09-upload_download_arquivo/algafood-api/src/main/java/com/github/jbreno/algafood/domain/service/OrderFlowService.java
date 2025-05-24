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
	public void confirm(String code) {
		Order order = issuanceOrder.searchOrFail(code);
		order.confirm();
	}
	
	@Transactional
	public void canceled(String code) {
		Order order = issuanceOrder.searchOrFail(code);
		order.canceled();
	}
	
	@Transactional
	public void delivered(String code) {
		Order order = issuanceOrder.searchOrFail(code);
		order.delivered();
	}
	
}
