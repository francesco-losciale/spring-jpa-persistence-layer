package persistence;

import commons.model.dto.ISimpleEntityDTO;
import commons.model.entity.BaseEntity;

@SuppressWarnings("unchecked")
public interface PersistenceManagerSupport<T extends ISimpleEntityDTO> {

	public IPersistenceEntityManager<T, BaseEntity> getPersistenceEntityManager();

	public void setPersistenceEntityManager(IPersistenceEntityManager<T, BaseEntity> persistenceEntityManager);

	public void setClassDto(Class<T> classDto);

	public void setClassEntity(Class<BaseEntity> classEntity);
}
