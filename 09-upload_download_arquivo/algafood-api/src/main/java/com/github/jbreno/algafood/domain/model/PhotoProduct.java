package com.github.jbreno.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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
}
