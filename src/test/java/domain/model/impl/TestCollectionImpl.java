package domain.model.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.persistence.base.BaseDomain;

import domain.model.Test;
import domain.model.TestCollection;

class TestCollectionImpl extends BaseDomain implements TestCollection {
	
	private Long id;
	private String releaseName;
	private Set<Test> listTest;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReleaseName() {
		return releaseName;
	}
	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}
	public List<Test> getListTest() {
		if (listTest == null) {
			return new ArrayList<Test>();
		}
		return new ArrayList<Test>(this.listTest);
	}
	public void addListTest(TestImpl test) {
		if (this.listTest == null) {
			this.listTest = new HashSet<Test>();
		}
		this.listTest.add(test);
	}
	public void removeListTest(TestImpl test) {
		if (this.listTest != null) {
			this.listTest.remove(test);
		}
	}	
	public void removeAll() {
		this.listTest = new HashSet<Test>();
	}
	
}
