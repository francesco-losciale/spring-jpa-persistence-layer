package domain.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.persistence.base.BaseRepository;

import domain.model.TestCollection;
import domain.model.impl.MapperHelper;
import domain.repository.TestCollectionRepository;
import persistence.entity.TestCollectionEntity;

@Repository
class TestCollectionRepositoryImpl extends BaseRepository<TestCollection, TestCollectionEntity> implements TestCollectionRepository {
	
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
