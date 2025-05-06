package com.github.jbreno.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbreno.algafood.api.openapi.controller.OrderFlowControllerOpenApi;
import com.github.jbreno.algafood.domain.service.OrderFlowService;

@RestController
@RequestMapping(value = "/orders/{code}")
public class OrderFlowController implements OrderFlowControllerOpenApi{
	
	@Autowired
	private OrderFlowService orderFlowService;
	
	@PutMapping("/confirmation")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirm(@PathVariable String code) {
		orderFlowService.confirm(code);
	}

	@PutMapping("/canceled")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void caceled(@PathVariable String code) {
		orderFlowService.canceled(code);
	}
	
	@PutMapping("/delivered")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delivered(@PathVariable String code) {
		orderFlowService.delivered(code);
	}
}
