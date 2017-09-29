package main.repository;

import com.dto.TestCollectionDTO;
import com.entity.TestCollectionEntity;

import persistence.IPersistenceEntityManager;

public interface ITestCollectionManager extends IPersistenceEntityManager<TestCollectionDTO, TestCollectionEntity>{

}
