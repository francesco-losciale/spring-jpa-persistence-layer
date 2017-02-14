package com.manager;

import com.dto.TestDTO;
import com.entity.TestEntity;

import persistence.IPersistenceEntityManager;

public interface ITestManager extends IPersistenceEntityManager<TestDTO, TestEntity>{

}
