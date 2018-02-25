package persistence.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.persistence.base.BaseRepositoryImpl;

import domain.model.Test;
import persistence.entity.TestEntity;
import persistence.repository.MapperHelper;
import persistence.repository.TestRepository;

@Repository
class TestRepositoryImpl extends BaseRepositoryImpl<Test, TestEntity> implements TestRepository {
	
	@Autowired
	MapperHelper mapperHelper;
	
	public TestRepositoryImpl() {
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
