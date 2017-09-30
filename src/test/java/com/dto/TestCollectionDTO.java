package com.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import persistence2.helpers.bean.EntityMapped;
import persistence2.helpers.dto.BaseEntityDTO;

@EntityMapped(value = "com.entity.TestCollectionEntity")
public class TestCollectionDTO extends BaseEntityDTO {
	private static final long serialVersionUID = 1L;

	private Set<TestDTO> listTest;
	private String releaseName;
	
	public Set<TestDTO> getListTest() {
		return listTest;
	}
	public void setListTest(Set<TestDTO> listTest) {
		this.listTest = listTest;
	}
	public void addTest(TestDTO test) {
		if (this.listTest == null) {
			this.listTest = new HashSet<TestDTO>();
		}
		this.listTest.add(test);
		test.setTestCollection(this);
	}
	public String getReleaseName() {
		return releaseName;
	}
	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}	
}
