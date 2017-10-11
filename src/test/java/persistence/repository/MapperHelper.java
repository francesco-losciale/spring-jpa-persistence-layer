package persistence.repository;

import java.lang.reflect.Type;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import domain.model.Test;
import domain.model.TestCollection;
import persistence.entity.TestCollectionEntity;
import persistence.entity.TestEntity;

@Component
public class MapperHelper {

	private ModelMapper modelMapper = new ModelMapper();
	
	public TestEntity convertToTestEntity(Test TestImpl) {
		TestEntity testEntity = modelMapper.map(TestImpl, TestEntity.class);
		testEntity.setTestCollection(convertToTestCollectionEntiy(TestImpl.getTestCollection()));
		return testEntity;
	}
	
	public Test convertToTest(TestEntity testEntity) {
		Test TestImpl = modelMapper.map(testEntity, Test.class);
		((Test)TestImpl).setTestCollection((TestCollection)convertToTestCollection(testEntity.getTestCollection()));
		return TestImpl;
	}
	
	public TestCollectionEntity convertToTestCollectionEntiy(TestCollection TestCollectionImpl) {
		Type listType = new TypeToken<Set<TestEntity>>() {}.getType();
		Set<TestEntity> listTestEntity = modelMapper.map(TestCollectionImpl.getListTest(), listType);
		TestCollectionEntity dest = modelMapper.map(TestCollectionImpl, TestCollectionEntity.class);
		dest.setListTest(listTestEntity);
		return dest;
	}
	
	public TestCollection convertToTestCollection(TestCollectionEntity testCollectionEntity) {
		Type destinationListType = new TypeToken<Set<Test>>() {}.getType();
		Set<Test> listTest = modelMapper.map(testCollectionEntity.getListTest(), destinationListType);
		TestCollection dest = modelMapper.map(testCollectionEntity, TestCollection.class);
		for (Test TestImpl : listTest) {
			((TestCollection)dest).addListTest((Test)TestImpl);
		}
		return dest;
	}
	
}
