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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jbreno.algafood.core.validation.Groups;
import com.github.jbreno.algafood.core.validation.ShippingFee;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tb_restaurant")
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String name;
	
//	@DecimalMin("1")
//	@PositiveOrZero
	@ShippingFee
	@Column(nullable = false)
	private BigDecimal shippingFee;
	
	
	@ManyToOne
	@NotNull
	@ConvertGroup(from = Default.class, to = Groups.KitchenId.class)
	@Valid
	@JoinColumn(name = "kitchen_id", nullable = false)
	private Kitchen kitchen;
	
	@JsonIgnore
	@Embedded
	private Address address;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime registrationDate;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false,  columnDefinition = "datetime")
	private LocalDateTime updateDate;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "tb_restaurant_payment_method",
			joinColumns = @JoinColumn(name = "restaurant_id"),
			inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
	private List<PaymentMethod> paymentsMethod = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(
	    name = "tb_restaurant_products",
	    joinColumns = @JoinColumn(name = "restaurant_id"),
	    inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	@JsonIgnore
	private List<Product> products = new ArrayList<>();
}
