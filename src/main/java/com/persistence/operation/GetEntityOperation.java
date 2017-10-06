package com.persistence.operation;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.persistence.base.BasePersistenceProvider;
import com.persistence.exception.OperationException;

@Repository
public class GetEntityOperation<EntityObjectType> extends BasePersistenceProvider implements IGetEntityOperation<EntityObjectType>  {

	/**
	 * WARNING: 
	 * If you are using Hibernate as the provider, it is possible that the object is "persisted" merely 
	 * in cache and the changes have not actually been pushed to the database. Accordingly, the criteria 
	 * going through Hibernate's implementation will retrieve the object, but the entity manager find will 
	 * not be able to locate the object.
	 */
	@Override
	public EntityObjectType get(Class<EntityObjectType> resultClass, Object id) throws OperationException {
		EntityManager em = getEntityManager();
		EntityObjectType resultEntity = null;
		try {		
			resultEntity = em.getReference(resultClass, id);
		} catch (Exception e) {
			throw new OperationException(e);
		}
		return resultEntity;
	}
	
	public EntityManager getEntityManager() {
		return super.getEntityManager();
	}

}
