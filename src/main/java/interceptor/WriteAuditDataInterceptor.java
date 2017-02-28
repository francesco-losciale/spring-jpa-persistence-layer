package interceptor;

import java.io.Serializable;
import java.util.Calendar;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import commons.model.entity.BaseEntity;

public class WriteAuditDataInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

		if (BaseEntity.class.isInstance(entity)) {
			BaseEntity baseEntity = (BaseEntity) entity;
			baseEntity.setDateInsert(Calendar.getInstance().getTime());
			baseEntity.setUserInsert("test2");
		}
		return super.onSave(entity, id, state, propertyNames, types);
	}
}
