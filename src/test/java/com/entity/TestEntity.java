package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import commons.model.entity.BaseEntity;

@Entity
@Table(name="TEST")
public class TestEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Id
	@Column(name="ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
