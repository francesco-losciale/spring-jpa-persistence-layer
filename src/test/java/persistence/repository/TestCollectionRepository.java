package persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.persistence.base.BaseRepository;

import main.domain.object.TestCollection;
import main.domain.repository.ITestCollectionRepository;
import persistence.entity.TestCollectionEntity;

@Repository
public class TestCollectionRepository extends BaseRepository<TestCollection, TestCollectionEntity> implements ITestCollectionRepository {
	
	@Autowired
	DomainMapperHelper mapperHelper;
	
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
