package com.dto;

import persistence.helpers.bean.EntityMapped;
import persistence.helpers.dto.BaseEntityDTO;

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
