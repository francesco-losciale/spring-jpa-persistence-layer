package persistence.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.persistence.base.BaseRepositoryImpl;

import domain.model.TestCollection;
import persistence.entity.TestCollectionEntity;
import persistence.repository.MapperHelper;
import persistence.repository.TestCollectionRepository;

@Repository
class TestCollectionRepositoryImpl extends BaseRepositoryImpl<TestCollection, TestCollectionEntity> implements TestCollectionRepository {
	
	@Autowired
	MapperHelper mapperHelper;
	
	public TestCollectionRepositoryImpl() {
		super(TestCollectionEntity.class);
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
