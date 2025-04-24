package com.github.jbreno.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tb_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false)
	private BigDecimal subtotal;
	
	@Column(nullable = false)
	private BigDecimal shippingFee;
	
	@Column(nullable = false)
	private BigDecimal totalValue;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dateCreated;
	
	private LocalDateTime confirmationDate;
	
	private LocalDateTime cancelletionDate;
	
	private LocalDateTime deliveryDate;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private PaymentMethod paymentMethod;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Restaurant restaurant;

	@OneToOne
	@JoinColumn(nullable = false)
	private User client;
	
	
	@Embedded
	@Column(nullable = false)
	private Address deliveryAddress;
	
	@Column(nullable = false)
	private OrderStatus status;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> itens = new ArrayList<>();
}
