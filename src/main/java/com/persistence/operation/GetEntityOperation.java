package com.persistence.operation;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.persistence.base.BasePersistenceProvider;

import persistence2.helpers.exception.OperationException;

@Repository
public class GetEntityOperation<EntityObjectType> extends BasePersistenceProvider implements IGetEntityOperation<EntityObjectType>  {

	@Override
	public EntityObjectType get(Class<EntityObjectType> resultClass, Long id) throws OperationException {
		EntityManager em = getEntityManager();
		EntityObjectType resultEntity = null;
		try {		
			resultEntity = em.find(resultClass, id);
		} catch (Exception e) {
			throw new OperationException(e);
		} 
		return resultEntity;
	}

}
