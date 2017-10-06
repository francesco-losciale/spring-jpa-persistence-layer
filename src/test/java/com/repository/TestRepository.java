package com.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.TestEntity;
import com.persistence.base.BaseRepository;

import main.domain.object.Test;
import main.domain.repository.ITestRepository;

@Repository
public class TestRepository extends BaseRepository<Test, TestEntity> implements ITestRepository {
	
	@Autowired
	TestObjectMapperHelper mapperHelper;
	
	public TestRepository() {
		super(TestEntity.class);
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
