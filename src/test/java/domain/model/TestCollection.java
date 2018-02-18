package domain.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestCollection extends BaseDomain {
	
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
	public void addListTest(Test test) {
		if (this.listTest == null) {
			this.listTest = new HashSet<Test>();
		}
		this.listTest.add(test);
	}
	public void removeListTest(Test test) {
		if (this.listTest != null) {
			this.listTest.remove(test);
		}
	}	
	public void removeAll() {
		this.listTest = new HashSet<Test>();
	}
	
}
