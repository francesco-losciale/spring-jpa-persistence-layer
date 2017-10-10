package domain.repository;

import java.util.List;

import com.persistence.base.IBaseRepository;

import domain.model.TestCollection;
import persistence.entity.TestCollectionEntity;


public interface ITestCollectionRepository extends IBaseRepository<TestCollection, TestCollectionEntity>{

	public List<TestCollection> getAll();
}
