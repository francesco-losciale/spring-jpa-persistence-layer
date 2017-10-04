package com.manager;

import java.lang.reflect.Type;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import com.dto.Test;
import com.dto.TestCollection;
import com.entity.TestCollectionEntity;
import com.entity.TestEntity;

@Component
public class TestObjectMapperHelper {

	private ModelMapper modelMapper = new ModelMapper();
	
	public TestEntity convertToTestEntity(Test test) {
		TestEntity testEntity = modelMapper.map(test, TestEntity.class);
		testEntity.setTestCollection(convertToTestCollectionEntiy(test.getTestCollection()));
		return testEntity;
	}
	
	public Test convertToTest(TestEntity testEntity) {
		Test test = modelMapper.map(testEntity, Test.class);
		test.setTestCollection(convertToTestCollection(testEntity.getTestCollection()));
		return test;
	}
	
	public TestCollectionEntity convertToTestCollectionEntiy(TestCollection testCollection) {
		Type listType = new TypeToken<Set<TestEntity>>() {}.getType();
		Set<TestEntity> listTestEntity = modelMapper.map(testCollection.getListTest(), listType);
		TestCollectionEntity dest = modelMapper.map(testCollection, TestCollectionEntity.class);
		dest.setListTest(listTestEntity);
		return dest;
	}
	
	public TestCollection convertToTestCollection(TestCollectionEntity testCollectionEntity) {
		Type destinationListType = new TypeToken<Set<Test>>() {}.getType();
		Set<Test> listTest = modelMapper.map(testCollectionEntity.getListTest(), destinationListType);
		TestCollection dest = modelMapper.map(testCollectionEntity, TestCollection.class);
		for (Test test : listTest) {
			dest.addListTest(test);
		}
		return dest;
	}
	
}
