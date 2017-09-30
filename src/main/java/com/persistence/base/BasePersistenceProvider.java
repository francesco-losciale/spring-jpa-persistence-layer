package com.persistence.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Session;

public class BasePersistenceProvider {

    @PersistenceContext(unitName = "jpaUnit", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
    
	@Deprecated
	public Session getSession() {
		return getEntityManager().unwrap(Session.class); // TODO decouple jpa from hibernate
	}
}
