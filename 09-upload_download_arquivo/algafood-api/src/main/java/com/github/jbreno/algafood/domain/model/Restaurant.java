package com.github.jbreno.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private BigDecimal shippingFee;
	
	
	@ManyToOne
	@JoinColumn(name = "kitchen_id", nullable = false)
	private Kitchen kitchen;
	
	@Embedded
	private Address address;
	
	private Boolean active = Boolean.TRUE;
	
	private Boolean open = Boolean.TRUE;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime registrationDate;
	
	@UpdateTimestamp
	@Column(nullable = false,  columnDefinition = "datetime")
	private LocalDateTime updateDate;
	
	@ManyToMany
	@JoinTable(name = "tb_restaurant_payment_method",
			joinColumns = @JoinColumn(name = "restaurant_id"),
			inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
	private Set<PaymentMethod> paymentsMethod = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "tb_restaurant_user",
			joinColumns = @JoinColumn(name = "restaurant_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> responsible = new HashSet<>();
	
	@OneToMany(mappedBy = "restaurant")
	private List<Product> products = new ArrayList<>();
	
	public void active() {
		setActive(true);
	}
	
	public void inactivate() {
		setActive(false);
	}
	
	public void open() {
		setOpen(true);
	}
	
	public void close() {
		setOpen(false);
	}
	
	public boolean removePaymentMethod(PaymentMethod paymentMethod) {
		return getPaymentsMethod().remove(paymentMethod);
	}
	
	public boolean addPaymentMethod(PaymentMethod paymentMethod) {
		return getPaymentsMethod().add(paymentMethod);
	}
	
	public boolean removeResponsible(User user) {
		return getResponsible().remove(user);
	}
	
	public boolean addResponsible(User user) {
		return getResponsible().add(user);
	}
	
	public Optional<Product> findProductById(Long productId) {
	    return this.getProducts().stream()
	        .filter(p -> p.getId().equals(productId))
	        .findFirst();
	}
}
