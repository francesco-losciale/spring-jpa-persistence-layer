package com.manager;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	
	public List<TestCollection> getAll() {
		
		CriteriaQuery<TestCollectionEntity> q = getCriteriaBuilder().createQuery(TestCollectionEntity.class);
		Root<TestCollectionEntity> c = q.from(TestCollectionEntity.class);		
		q.select(c);
		
		List<TestCollectionEntity> testCollectionEntity = executeQuery(q);
		List<TestCollection> listTestCollection = testCollectionEntity.stream().map(t -> convert(t)).collect(Collectors.toList());
		
		return listTestCollection;
	}
}
