package persistence.repository;

import java.util.List;

import com.persistence.base.IBaseRepository;

import domain.model.Test;
import persistence.entity.TestEntity;

public interface TestRepository extends IBaseRepository<Test, TestEntity>{

	public List<Test> getAll();
}
