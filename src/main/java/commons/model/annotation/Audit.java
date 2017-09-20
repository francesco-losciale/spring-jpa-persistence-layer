package commons.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Audit {
	
	public enum AuthenticationType {
		USER_AUTHENTICATION,
		SYSTEM_AUTHENTICATION,
		BATCH_AUTHENTICATION,
		DEFAULT
	};

	AuthenticationType authenticationType() default AuthenticationType.USER_AUTHENTICATION;
}
