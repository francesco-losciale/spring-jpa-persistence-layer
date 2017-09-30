package main.repository;

import com.dto.TestDTO;
import com.entity.TestEntity;

import persistence2.IPersistenceEntityManager;

public interface ITestManager extends IPersistenceEntityManager<TestDTO, TestEntity>{

}
