package com.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.TestCollection;
import com.entity.TestCollectionEntity;
import com.persistence.base.BaseRepository;

import main.repository.ITestCollectionManager;

@Repository
public class TestCollectionManager extends BaseRepository<TestCollection, TestCollectionEntity> implements ITestCollectionManager {
	
	@Autowired
	TestObjectMapperHelper mapperHelper;
	
	public TestCollectionManager() {
		super(TestCollection.class, TestCollectionEntity.class);
	}	

	@Override
	public TestCollection convert(TestCollectionEntity entityObject) {		
		return mapperHelper.convertToTestCollection(entityObject);
	}

	@Override
	public TestCollectionEntity convert(TestCollection domainObject) {		
		return mapperHelper.convertToTestCollectionEntiy(domainObject);
	}
	
}
