package domain.component;

import org.springframework.stereotype.Component;

import domain.model.Test;
import domain.model.TestCollection;

@Component
public class ModelObjectFactory {

	public Test createTest(TestCollection testCollection) {
		Test obj = new Test();
		obj.setTestCollection((TestCollection)testCollection);
		((TestCollection)testCollection).addListTest(obj);
		return obj;
	}
	
	public TestCollection createTestCollection(String releaseName) {
		TestCollection obj = new TestCollection();
		obj.setReleaseName(releaseName);
		return obj;		
	}
}
