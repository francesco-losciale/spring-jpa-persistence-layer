package domain.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.persistence.base.BaseRepository;

import domain.model.Test;
import domain.model.impl.MapperHelper;
import domain.repository.TestRepository;
import persistence.entity.TestEntity;

@Repository
class TestRepositoryImpl extends BaseRepository<Test, TestEntity> implements TestRepository {
	
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
