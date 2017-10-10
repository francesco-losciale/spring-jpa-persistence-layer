package domain.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.persistence.base.BaseRepository;

import domain.model.TestCollection;
import domain.model.impl.MapperHelper;
import domain.repository.ITestCollectionRepository;
import persistence.entity.TestCollectionEntity;

@Repository
class TestCollectionRepository extends BaseRepository<TestCollection, TestCollectionEntity> implements ITestCollectionRepository {
	
	@Autowired
	MapperHelper mapperHelper;
	
	public TestCollectionRepository() {
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
