package com.github.jbreno.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.github.jbreno.algafood.domain.exception.BusinessException;

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
	
	private String code;
	
	@Column(nullable = false)
	private BigDecimal subtotal;
	
	@Column(nullable = false)
	private BigDecimal shippingFee;
	
	@Column(nullable = false)
	private BigDecimal totalValue;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime", name = "creation_date")
	private OffsetDateTime dateCreated;
	
	private OffsetDateTime confirmationDate;
	
	private OffsetDateTime cancellationDate;
	
	private OffsetDateTime deliveryDate;
	
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
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> itens = new ArrayList<>();
	
	public void calculateTotalValue() {
	    if (this.itens == null) {
	        this.itens = new ArrayList<>();
	    }

	    this.itens.forEach(OrderItem::calculateTotalValue);

	    this.subtotal = this.itens.stream()
	            .map(item -> item.getTotalPrice() != null ? item.getTotalPrice() : BigDecimal.ZERO)
	            .reduce(BigDecimal.ZERO, BigDecimal::add);

	    if (this.shippingFee == null) {
	        this.shippingFee = BigDecimal.ZERO;
	    }

	    this.totalValue = this.subtotal.add(this.shippingFee);
	}	
	public void defineFreight() {
		setShippingFee(getRestaurant().getShippingFee());
	}
	
	public void confirm() {
		setStatus(OrderStatus.CONFIRMED);
		setConfirmationDate(OffsetDateTime.now());
	}
	
	public void delivered() {
		setStatus(OrderStatus.DELIVERED);
		setDeliveryDate(OffsetDateTime.now());
	}
	
	public void canceled() {
		setStatus(OrderStatus.CANCELED);
		setCancellationDate(OffsetDateTime.now());
	}
	
	private void setStatus(OrderStatus newStatus) {
		if(getStatus().cannnotChangeTo(newStatus)) {
			throw new BusinessException(
					String.format("Status do pedido %s não pode ser alterado de %s para %s", 
							getCode(), getStatus().getDescription(), newStatus.getDescription()));
		}
		
		this.status = newStatus;
	}
	
	@PrePersist
	private void generatedCode() {
		setCode(UUID.randomUUID().toString());
	}
}
