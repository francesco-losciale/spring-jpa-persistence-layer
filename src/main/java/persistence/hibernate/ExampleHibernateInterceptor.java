package persistence.hibernate;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class ExampleHibernateInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean onSave(	Object entity, 
							Serializable id, 
							Object[] state, 
							String[] propertyNames, 
							Type[] types) {
		
		// TODO
		return super.onSave(entity, id, state, propertyNames, types);
	}
		
	@Override
	public boolean onFlushDirty(
			Object entity, 
			Serializable id, 
			Object[] currentState, 
			Object[] previousState, 
			String[] propertyNames, 
			Type[] types) {
		
		// TODO
		return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);

	}
	
	
	
}
