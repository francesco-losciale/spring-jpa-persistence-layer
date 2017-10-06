package persistence.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.persistence.base.BaseRepository;

import main.domain.object.Test;
import main.domain.repository.ITestRepository;
import persistence.entity.TestEntity;

@Repository
public class TestRepository extends BaseRepository<Test, TestEntity> implements ITestRepository {
	
	@Autowired
	DomainMapperHelper mapperHelper;
	
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

	public List<Test> getAll() {
		
		CriteriaQuery<TestEntity> q = getCriteriaBuilder().createQuery(TestEntity.class);
		Root<TestEntity> c = q.from(TestEntity.class);		
		q.select(c);
		
		List<TestEntity> testEntity = executeQuery(q, c);
		List<Test> listTest = testEntity.stream().map(t -> convert(t)).collect(Collectors.toList());
		
		return listTest;
	}
}
