package domain.util;

import org.springframework.stereotype.Service;

import domain.model.Test;
import domain.model.TestCollection;
import domain.util.ModelObjectFactory;

@Service // TODO is it correct?
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
