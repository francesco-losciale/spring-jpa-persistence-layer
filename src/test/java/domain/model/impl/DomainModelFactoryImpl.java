package domain.model.impl;

import org.springframework.stereotype.Service;

import domain.model.DomainModelFactory;
import domain.model.Test;
import domain.model.TestCollection;

@Service // TODO is it correct?
public class DomainModelFactoryImpl implements DomainModelFactory {

	public Test createTest(TestCollection testCollection) {
		TestImpl obj = new TestImpl();
		obj.setTestCollection((TestCollectionImpl)testCollection);
		((TestCollectionImpl)testCollection).addListTest(obj);
		return obj;
	}
	
	public TestCollection createTestCollection(String releaseName) {
		TestCollectionImpl obj = new TestCollectionImpl();
		obj.setReleaseName(releaseName);
		return obj;		
	}
}
