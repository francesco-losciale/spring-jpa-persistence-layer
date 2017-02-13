package com.entity;

import javax.persistence.Entity;

import commons.model.entity.BaseEntity;

@Entity
public class TestEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Override
	public Long getId() {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public void setId(Long id) {
		throw new RuntimeException("not implemented yet");
	}

}
