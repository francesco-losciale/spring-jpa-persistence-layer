package persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import commons.model.exception.ManagerException;

public class BasePersistenceManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		if (this.sessionFactory == null) throw new ManagerException("Hibernate Session is closed");
		return sessionFactory.getCurrentSession();
	}
}
