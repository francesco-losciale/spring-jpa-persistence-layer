package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import commons.model.entity.BaseEntity;

@Entity
@Table(name="TEST")
public class TestEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private Long id;
	private TestCollectionEntity testCollection;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_TEST")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="ID_TEST_COLLECTION", nullable=true, insertable = true)
	public TestCollectionEntity getTestCollection() {
		return testCollection;
	}

	public void setTestCollection(TestCollectionEntity testCollection) {
		this.testCollection = testCollection;
	}

	
}
