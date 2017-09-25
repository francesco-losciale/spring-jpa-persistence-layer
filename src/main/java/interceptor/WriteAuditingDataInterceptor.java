package interceptor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import commons.model.custom.annotation.Audit;
import commons.model.entity.BaseEntity;

public class WriteAuditingDataInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean onSave(	Object entity, 
							Serializable id, 
							Object[] state, 
							String[] propertyNames, 
							Type[] types) {
		if (entity.getClass().isAnnotationPresent(Audit.class)) {
			if (((BaseEntity)entity).getDateInsert() == null) {
				state[Arrays.asList(propertyNames).indexOf("dateInsert")] = Calendar.getInstance().getTime();
				state[Arrays.asList(propertyNames).indexOf("userInsert")] = "user_insert";	
			} else {
				state[Arrays.asList(propertyNames).indexOf("dateModify")] = Calendar.getInstance().getTime();
				state[Arrays.asList(propertyNames).indexOf("userModify")] = "user_modify";
			}			
			return true;
		}
		return false;		
	}
	
	
		
	@Override
	public boolean onFlushDirty(
			Object entity, 
			Serializable id, 
			Object[] currentState, 
			Object[] previousState, 
			String[] propertyNames, 
			Type[] types) {
		
		if (entity.getClass().isAnnotationPresent(Audit.class)) {
			if (((BaseEntity)entity).getDateInsert() == null) {
				currentState[Arrays.asList(propertyNames).indexOf("dateInsert")] = Calendar.getInstance().getTime();
				currentState[Arrays.asList(propertyNames).indexOf("userInsert")] = "user_insert";	
			} else {
				currentState[Arrays.asList(propertyNames).indexOf("dateModify")] = Calendar.getInstance().getTime();
				currentState[Arrays.asList(propertyNames).indexOf("userModify")] = "user_modify";
			}			
			return true;
		}
		return false;		
	}
	
	
}
