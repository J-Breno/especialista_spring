package com.github.jbreno.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@Column(nullable = false, columnDefinition = "datetime", name = "creation_date")
	private LocalDateTime dateCreated;
	
	private LocalDateTime confirmationDate;
	
	private LocalDateTime cancellationDate;
	
	private LocalDateTime deliveryDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private PaymentMethod paymentMethod;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurant restaurant;

	@ManyToOne
	@JoinColumn(nullable = false, name = "user_id")
	private User client;
	
	
	@Embedded
	@Column(nullable = false)
	private Address deliveryAddress;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.CREATING;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> itens = new ArrayList<>();
	
	public void calculateTotalValue() {
		this.subtotal = getItens().stream()
				.map(item -> item.getTotalPrice())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		this.totalValue = this.subtotal.add(this.shippingFee);
	}
	
	public void defineFreight() {
		setShippingFee(getRestaurant().getShippingFee());
	}
}
