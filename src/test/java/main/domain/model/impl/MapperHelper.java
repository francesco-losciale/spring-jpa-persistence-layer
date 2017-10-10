package main.domain.model.impl;

import java.lang.reflect.Type;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import main.domain.model.Test;
import main.domain.model.TestCollection;
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
	
	public TestImpl convertToTest(TestEntity testEntity) {
		TestImpl TestImpl = modelMapper.map(testEntity, TestImpl.class);
		((TestImpl)TestImpl).setTestCollection((TestCollectionImpl)convertToTestCollection(testEntity.getTestCollection()));
		return TestImpl;
	}
	
	public TestCollectionEntity convertToTestCollectionEntiy(TestCollection TestCollectionImpl) {
		Type listType = new TypeToken<Set<TestEntity>>() {}.getType();
		Set<TestEntity> listTestEntity = modelMapper.map(TestCollectionImpl.getListTest(), listType);
		TestCollectionEntity dest = modelMapper.map(TestCollectionImpl, TestCollectionEntity.class);
		dest.setListTest(listTestEntity);
		return dest;
	}
	
	public TestCollectionImpl convertToTestCollection(TestCollectionEntity testCollectionEntity) {
		Type destinationListType = new TypeToken<Set<TestImpl>>() {}.getType();
		Set<TestImpl> listTest = modelMapper.map(testCollectionEntity.getListTest(), destinationListType);
		TestCollectionImpl dest = modelMapper.map(testCollectionEntity, TestCollectionImpl.class);
		for (TestImpl TestImpl : listTest) {
			((TestCollectionImpl)dest).addListTest((TestImpl)TestImpl);
		}
		return dest;
	}
	
}
