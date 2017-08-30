package com.dto;

import commons.model.bean.EntityMapped;
import commons.model.dto.BaseEntityDTO;

@EntityMapped(value = "com.entity.TestEntity")
public class TestDTO extends BaseEntityDTO {
	private static final long serialVersionUID = 1L;

	private TestCollectionDTO testCollection;

	public TestCollectionDTO getTestCollection() {
		return testCollection;
	}
	public void setTestCollection(TestCollectionDTO testCollection) {
		this.testCollection = testCollection;
	}		
}
