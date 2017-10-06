package main.repository;

import java.util.List;

import com.entity.TestCollectionEntity;
import com.persistence.base.IBaseRepository;

import main.domain.object.TestCollection;


public interface ITestCollectionRepository extends IBaseRepository<TestCollection, TestCollectionEntity>{

	public List<TestCollection> getAll();
}
