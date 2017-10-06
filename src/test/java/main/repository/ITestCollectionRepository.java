package main.repository;

import java.util.List;

import com.dto.TestCollection;
import com.entity.TestCollectionEntity;
import com.persistence.base.IBaseRepository;


public interface ITestCollectionRepository extends IBaseRepository<TestCollection, TestCollectionEntity>{

	public List<TestCollection> getAll();
}
