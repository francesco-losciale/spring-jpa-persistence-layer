package domain.model.impl;

import com.persistence.base.BaseDomain;

import domain.model.Test;

class TestImpl extends BaseDomain implements Test {
	
	private Long id;
	private TestCollectionImpl testCollection;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TestCollectionImpl getTestCollection() {
		return testCollection;
	}
	public void setTestCollection(TestCollectionImpl testCollection) {
		this.testCollection = testCollection;
	}		
}
