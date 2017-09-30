package persistence2.helpers.operation;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import persistence2.helpers.bean.OperationMetadata;
import persistence2.helpers.entity.BaseEntity;
import persistence2.helpers.exception.OperationException;

@Repository
@SuppressWarnings("unchecked")
public class SaveEntityOperation<E extends BaseEntity> extends BaseEntityOperator<E> implements ISaveEntityOperation<E> {

	public final static String NAME = "saveEntityOperation";

	public SaveEntityOperation() {
		super();
	}

	public E save(E entity, OperationMetadata operationMetadata) throws OperationException {
		EntityManager em = getEntityManager();
		try {		
			if (entity.getId() == null) 
				em.persist(entity);
			else {
				entity = em.merge(entity);
			}
		} catch (Exception e) {
			throw new OperationException(e);
		} 
		return entity;
	}

}
