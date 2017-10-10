package main.domain.repository;

import java.util.List;

import com.persistence.base.IBaseRepository;

import main.domain.model.Test;
import persistence.entity.TestEntity;

public interface ITestRepository extends IBaseRepository<Test, TestEntity>{

	public List<Test> getAll();
}
