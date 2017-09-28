package commons.model.operation;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import commons.model.bean.OperationMetadata;
import commons.model.entity.BaseEntity;
import commons.model.exception.OperationException;

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
