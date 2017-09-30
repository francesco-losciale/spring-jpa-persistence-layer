package com.manager;

import org.springframework.stereotype.Repository;

import com.dto.TestDTO;
import com.entity.TestEntity;

import main.repository.ITestManager;
import persistence2.PersistenceEntityManager;

@Repository
public class TestManager extends PersistenceEntityManager<TestDTO, TestEntity> implements ITestManager {

}
