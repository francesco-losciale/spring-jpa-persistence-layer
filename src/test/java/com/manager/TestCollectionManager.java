package com.manager;

import org.springframework.stereotype.Repository;

import com.dto.TestCollectionDTO;
import com.entity.TestCollectionEntity;

import persistence.PersistenceEntityManager;

@Repository
public class TestCollectionManager extends PersistenceEntityManager<TestCollectionDTO, TestCollectionEntity> implements ITestCollectionManager {

}
