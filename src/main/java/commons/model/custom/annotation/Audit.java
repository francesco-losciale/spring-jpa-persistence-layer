package commons.model.custom.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Audit {
	
	public enum AuditDataSource {
		USER_SESSION,
		SYSTEM_SESSION,
		DEFAULT
	};

	AuditDataSource auditDataSource() default AuditDataSource.DEFAULT;
}
