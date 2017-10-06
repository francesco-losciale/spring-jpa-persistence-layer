package main.domain.object;

import com.persistence.base.BaseDomain;

public class Test extends BaseDomain {
	
	private Long id;
	private TestCollection testCollection;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TestCollection getTestCollection() {
		return testCollection;
	}
	public void setTestCollection(TestCollection testCollection) {
		this.testCollection = testCollection;
	}		
}
