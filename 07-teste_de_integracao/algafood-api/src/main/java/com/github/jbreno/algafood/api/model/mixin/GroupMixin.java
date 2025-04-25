package com.github.jbreno.algafood.api.model.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jbreno.algafood.domain.model.Permission;

public class GroupMixin {
	
	@JsonIgnore
	private List<Permission> permissions;
}
