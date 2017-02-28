package com.dto;

import java.util.ArrayList;
import java.util.List;

import commons.model.bean.EntityMapped;
import commons.model.dto.BaseEntityDTO;

@EntityMapped(value = "com.entity.TestCollectionEntity")
public class TestCollectionDTO extends BaseEntityDTO {
	private static final long serialVersionUID = 1L;

	private List<TestDTO> listTest;
	private String releaseName;
	
	public List<TestDTO> getListTest() {
		return listTest;
	}
	public void setListTest(List<TestDTO> listTest) {
		this.listTest = listTest;
	}
	public void addTestDTO(TestDTO test) {
		if (this.listTest == null) {
			this.listTest = new ArrayList<TestDTO>();
		}
		this.listTest.add(test);
		test.setTestCollectionDTO(this);
	}
	public String getReleaseName() {
		return releaseName;
	}
	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}	
}
