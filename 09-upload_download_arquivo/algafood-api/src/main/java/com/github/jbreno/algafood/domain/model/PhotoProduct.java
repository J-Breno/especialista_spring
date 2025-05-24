package com.github.jbreno.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tb_photo_product")
public class PhotoProduct {
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "product_id")
	private Long id;
	private String fileName;
	private String description;
	private String contentType;
	private Long size;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Product product;
	
	public Long getRestaurantId() {
		if(getProduct() != null) {
			return getProduct().getRestaurant().getId();
		}
		return null;
	}
}
