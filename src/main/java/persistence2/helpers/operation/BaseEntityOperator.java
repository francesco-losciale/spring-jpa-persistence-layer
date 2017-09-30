package persistence2.helpers.operation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Session;

import persistence2.helpers.entity.BaseEntity;

abstract class BaseEntityOperator<E extends BaseEntity> extends BaseOperation<E> {

    @PersistenceContext(unitName = "jpaUnit", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
    
	public Session getSession() {
		return getEntityManager().unwrap(Session.class); // TODO decouple jpa from hibernate
	}
}
