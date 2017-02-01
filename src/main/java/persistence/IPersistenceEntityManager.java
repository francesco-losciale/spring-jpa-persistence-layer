package persistence;

import java.util.List;

import commons.model.bean.Metadata;
import commons.model.bean.OperationMetadata;
import commons.model.dto.ISimpleEntityDTO;
import commons.model.entity.IBaseEntity;

public interface IPersistenceEntityManager<T extends ISimpleEntityDTO, E extends IBaseEntity> {

	public void setPersistentClass(Class<T> persistentClass);

	public T read(Long id, OperationMetadata metadata);

	public T create(T o, OperationMetadata metadata);

	public T update(T o, OperationMetadata metadata);

	public T delete(T o, OperationMetadata metadata);

	public List<T> list(OperationMetadata metadata);

	public int count(OperationMetadata metadata);

	public List<T> simpleQuery(String hsql, OperationMetadata metadata);

	public List<T> findByExample(T entity, Metadata... metadata);

	public List<T> findByExample(T entity, String[] orders, Metadata... metadata);

	public List<T> listAll(OperationMetadata metadata);

	public int countAll(OperationMetadata metadata);

	public boolean exists(Long id, OperationMetadata metadata);
}
