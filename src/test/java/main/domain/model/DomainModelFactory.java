package main.domain.model;

public interface DomainModelFactory {

	public Test createTest(TestCollection testCollection);
	
	public TestCollection createTestCollection(String releaseName);
	
}
