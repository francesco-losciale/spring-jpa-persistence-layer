package com.interceptor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.main.test.AppInfo;

import commons.model.annotation.Audit;

public class WriteAuditingDataInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean onSave(	Object entity, 
							Serializable id, 
							Object[] state, 
							String[] propertyNames, 
							Type[] types) {
		if (entity.getClass().isAnnotationPresent(Audit.class)) {
			switch (entity.getClass().getAnnotation(Audit.class).authenticationType()) {
			case USER_AUTHENTICATION:
				state[Arrays.asList(propertyNames).indexOf("dateInsert")] = Calendar.getInstance().getTime();
				state[Arrays.asList(propertyNames).indexOf("userInsert")] = AppInfo.getUserDetails().getUsername();	
				return true;	
			default:
			}
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
			switch (entity.getClass().getAnnotation(Audit.class).authenticationType()) {
			case USER_AUTHENTICATION:
				currentState[Arrays.asList(propertyNames).indexOf("dateModify")] = Calendar.getInstance().getTime();
				currentState[Arrays.asList(propertyNames).indexOf("userModify")] = AppInfo.getUserDetails().getUsername();
				return true;
			default:
			}
		}
		return false;		
	}
	
	
}
