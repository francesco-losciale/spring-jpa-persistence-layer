package com.manager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.Test;
import com.entity.TestEntity;
import com.persistence.base.BaseRepository;

import main.repository.ITestManager;

@Repository
public class TestManager extends BaseRepository<Test, TestEntity> implements ITestManager {
	
	@Autowired
	TestObjectMapperHelper mapperHelper;
	
	public TestManager() {
		super(Test.class, TestEntity.class);
	}

	@Override
	public Test convert(TestEntity entityObject) {
		return mapperHelper.convertToTest(entityObject);
	}

	@Override
	public TestEntity convert(Test domainObject) {
		return mapperHelper.convertToTestEntity(domainObject);
	}
}
