package main.domain.object;

import com.persistence.base.BaseDomain;

public class Test extends BaseDomain {
	
	private TestCollection testCollection;

	public TestCollection getTestCollection() {
		return testCollection;
	}
	public void setTestCollection(TestCollection testCollection) {
		this.testCollection = testCollection;
	}		
}
