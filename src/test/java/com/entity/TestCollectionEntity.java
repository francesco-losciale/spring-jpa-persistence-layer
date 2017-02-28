package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import commons.model.entity.BaseEntity;

@Entity
@Table(name="TEST_COLLECTION")
public class TestCollectionEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String releaseName;
	private List<TestEntity> listTest;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
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
	
	@OneToMany(mappedBy="testCollection", cascade=CascadeType.ALL)
	public List<TestEntity> getListTest() {
		return listTest;
	}
	public void setListTest(List<TestEntity> listTest) {
		this.listTest = listTest;
	}
	
	public void addTest(TestEntity testEntity) {
		if (this.listTest == null) {
			this.listTest = new ArrayList<>();
		}
		this.listTest.add(testEntity);
		testEntity.setTestCollection(this);
	}
	
	
	
	
}
