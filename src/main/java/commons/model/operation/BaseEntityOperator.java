package commons.model.operation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import commons.model.entity.BaseEntity;

abstract class BaseEntityOperator<E extends BaseEntity> extends BaseOperation<E> {

	@Autowired
	private SessionFactory sessionFactory;

	public BaseEntityOperator() {
		super();
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
