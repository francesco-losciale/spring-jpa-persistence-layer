package com.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import persistence2.helpers.entity.BaseEntity;

@Entity
@Table(name="TEST_COLLECTION")
@SequenceGenerator(name="SQ_TEST_COLLECTION", initialValue=1, allocationSize=100)
public class TestCollectionEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String releaseName;
	private Set<TestEntity> listTest;
	
	@Id
	@SequenceGenerator(name="SQ_TEST_COLLECTION_GENERATOR", sequenceName="SQ_TEST_COLLECTION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_TEST_COLLECTION_GENERATOR")
	@Column(name="ID_TEST_COLLECTION")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="RELEASE_NAME")
	public String getReleaseName() {
		return releaseName;
	}
	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}
	
	@OneToMany(mappedBy="testCollection", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	public Set<TestEntity> getListTest() {
		return listTest;
	}
	public void setListTest(Set<TestEntity> listTest) {
		this.listTest = listTest;
	}
	
	public void addTest(TestEntity testEntity) {
		if (this.listTest == null) {
			this.listTest = new HashSet<>();
		}
		this.listTest.add(testEntity);
		testEntity.setTestCollection(this);
	}
		
	
}
